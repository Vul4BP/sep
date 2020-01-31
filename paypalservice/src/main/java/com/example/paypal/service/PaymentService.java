package com.example.paypal.service;

import com.example.paypal.config.VarConfig;
import com.example.paypal.dto.CreatePaymentRequestDto;
import com.example.paypal.dto.CreatePaymentResponseDto;
import com.example.paypal.dto.ExecutePaymentRequestDto;
import com.example.paypal.dto.PaypalResponseDto;
import com.example.paypal.model.MyPayment;
import com.example.paypal.model.Seller;
import com.example.paypal.repository.PaymentRepository;
import com.example.paypal.repository.SellerRepository;
import com.example.paypal.utils.MyPaymentUtils;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService implements IPaymentService{

    private final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public CreatePaymentResponseDto createPayment(CreatePaymentRequestDto request) {

        Seller seller = sellerRepository.findByEmail(request.getEmail());

        MyPayment myPayment = new MyPayment();
        myPayment = paymentRepository.save(myPayment);

        Transaction transaction = MyPaymentUtils.setTransaction(request.getAmount(), seller.getPaypalEmail());  //paypal email
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payment payment = new Payment();
        payment.setIntent(VarConfig.intent);
        payment.setPayer(MyPaymentUtils.generatePayer(VarConfig.paymentMethod));
        payment.setTransactions(transactions);
        payment.setRedirectUrls(MyPaymentUtils.setRedirectUrls(myPayment.getId()));

        APIContext apiContext = new APIContext(VarConfig.clinetId, VarConfig.secret, VarConfig.mode);

        Payment createdPayment = null;
        PaypalResponseDto responseDto = new PaypalResponseDto();
        try {
            createdPayment = payment.create(apiContext);

            responseDto.setApprovalUrl(createdPayment.getLinks().get(VarConfig.paypalApprovalUrlIndex).getHref());
            responseDto.setPaymentId(createdPayment.getId());

            System.out.println(responseDto.getApprovalUrl());
            System.out.println(responseDto.getPaymentId());

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        myPayment.setAmount(request.getAmount());
        myPayment.setSeller(seller);
        myPayment.setPaymentId(createdPayment.getId());
        myPayment.setStatus(createdPayment.getState());
        paymentRepository.save(myPayment);

        CreatePaymentResponseDto res = new CreatePaymentResponseDto();
        res.setApprovalUrl(responseDto.getApprovalUrl());
        res.setId(responseDto.getPaymentId());
        return res;
    }

    @Override
    public String executePayment(ExecutePaymentRequestDto request) {
        String paymentId = request.getPaymentId();
        String payerId = request.getPayerId();

        LOGGER.info("PaymentID: " + paymentId);
        LOGGER.info("PayerID: " + payerId);

        //Long id = Long.parseLong(paymentId);
        MyPayment myPayment = paymentRepository.findByPaymentId(paymentId);
        Seller seller = myPayment.getSeller();
        APIContext apiContext = new APIContext(VarConfig.clinetId, VarConfig.secret, VarConfig.mode);

        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        try {
            Payment executedPayment = payment.execute(apiContext, paymentExecution);
            LOGGER.info(Payment.getLastResponse());

            Authorization authorization = executedPayment.getTransactions().get(0).getRelatedResources().get(0).getAuthorization();
            Amount amount = MyPaymentUtils.setAmount(myPayment.getAmount());

            Capture capture = new Capture();
            capture.setAmount(amount);
            capture.setIsFinalCapture(true);
            Capture responseCapture = authorization.capture(apiContext, capture);

            LOGGER.info("Capture id=" + responseCapture.getId() + " and status=" + responseCapture.getState());

            //--------------------TEST---------------------
            //myPayment.setStatus("Success");
            paymentRepository.save(myPayment);

            LOGGER.info("Executed payment - Request: \n" + Payment.getLastRequest());
            LOGGER.info("Executed payment - Response: \n" + Payment.getLastResponse());

        } catch (PayPalRESTException e) {
            e.printStackTrace();
            //LOGGER.info("CANCELED");
        }

        return VarConfig.returnUrl;
    }

    @Override
    public List<MyPayment> findAllByStatus(String status) {
        return paymentRepository.getAllByStatus(status);
    }

    @Override
    public String cancelPayment(Long id) {
        LOGGER.error("Payment canceled...");
        MyPayment myPayment = paymentRepository.getOne(id);
        myPayment.setStatus(VarConfig.paymentStatusFailed);
        paymentRepository.save(myPayment);
        return VarConfig.returnUrl;
    }

    @Override
    public String errorPayment(Long id) {
        LOGGER.error("Error in payment...");
        MyPayment myPayment = paymentRepository.getOne(id);
        myPayment.setStatus(VarConfig.paymentStatusError);
        paymentRepository.save(myPayment);
        return VarConfig.returnUrl;
    }
}

package com.example.bitcoin.service;

import com.example.bitcoin.config.VarConfig;
import com.example.bitcoin.model.Payment;
import com.example.bitcoin.model.Seller;
import com.example.bitcoin.modelDto.PaymentDto;
import com.example.bitcoin.modelDto.RequestDto;
import com.example.bitcoin.modelDto.ResponseDto;
import com.example.bitcoin.modelDto.SellerDto;
import com.example.bitcoin.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServ implements PaymentService {
    private final Logger LOGGER = LoggerFactory.getLogger(PaymentServ.class);

    private final PaymentRepository paymentRepository;
    private final SellerService sellerService;

    public PaymentServ(PaymentRepository paymentRepository, SellerService sellerService) {
        this.paymentRepository = paymentRepository;
        this.sellerService = sellerService;
    }

    @Override
    public PaymentDto preparePayment(RequestDto requestDto) {
        LOGGER.info("Preparing payment...");

        PaymentDto paymentDto = new PaymentDto();
        SellerDto seller = sellerService.findByEmail(requestDto.getEmail());
        Payment savedPayment = paymentRepository.save(new Payment());

        paymentDto.setPaymentId(savedPayment.getId());
        paymentDto.setAmount(requestDto.getAmount());
        paymentDto.setApiToken(seller.getApiToken());
        paymentDto.setCurrency(VarConfig.paymentCurrency);
        paymentDto.setTitle(VarConfig.paymentTitle);
        paymentDto.setSuccessUrl(VarConfig.paymentSuccessUrl + savedPayment.getId());
        paymentDto.setCancelUrl(VarConfig.paymentCancelUrl + savedPayment.getId());

        LOGGER.info("Prepared payment info: " + paymentDto.toString());
        return paymentDto;
    }

    @Override
    public Payment persist(ResponseDto responseDto, PaymentDto paymentDto) {
        Payment payment = paymentRepository.getOne(paymentDto.getPaymentId());
        Seller seller = sellerService.findByApiToken(paymentDto.getApiToken());

        payment.setOrderId(responseDto.getId());
        payment.setSeller(seller);
        payment.setAmount(paymentDto.getAmount());
        payment.setStatus(responseDto.getStatus());

        LOGGER.info("Persisting payment: " + payment.toString());

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> findAllByStatus(String status) {
        return paymentRepository.getAllByStatus(status);
    }

    @Override
    public Payment getById(Long id) {
        return paymentRepository.getOne(id);
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}

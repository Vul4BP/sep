import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { KpService } from 'src/services/kp.service';
import { ActivatedRoute, Router } from '@angular/router';

declare var paypal;

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})

export class PaymentComponent implements OnInit {
  @ViewChild('paypal', { static: true }) paypalElement: ElementRef;

  private itemId = "";

  constructor(private kpService: KpService, private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe(params => {
      this.itemId = params['id'];
    });
  }

  ngOnInit() {
    this.kpService.getItem(this.itemId)
      .subscribe(
        resp => {
          console.log(resp);
          var price = resp['amount'];
          var email = resp['seller']['email'];
          
          var returnUrl = "https://localhost:5004";
          paypal.Button.render({
            env: 'sandbox',
            // 1. Add a payment callback
            payment: function(data, actions) {
              console.log(data);
              // 2. Make a request to your server
              return fetch('https://localhost:8443/paypalservice/createPayment',{
                method:'post',
                body:JSON.stringify({
                  email: email,
                  amount: price
                }),
                headers:{
                  'Accept': 'application/json',
                  'Content-Type':'application/json',
                }
              }).then(res => {
                  if (res.ok) {
                    return res.json().then(json => {
                      console.log("JSON ID:" + json.id);
                      return json.id;
                    });
                  }
                  // 3. Return res.id from the response
                  //window.location.href=res.approvalUrl;
                  //return res.id;
                }, err => {
                  console.log(err);
                });
            },
            // Execute the payment:
            // 1. Add an onAuthorize callback
            onAuthorize: function(data, actions) {
              // 2. Make a request to your server
              return fetch('https://localhost:8443/paypalservice/execute',{ 
                method:'post',
                body:JSON.stringify({
                    paymentId: data.paymentID,
                    payerId:   data.payerID
                }),
                headers:{
                  'Accept': 'application/json',
                  'Content-Type':'application/json',
                }
              })
                .then(function(res) {
                  console.log(res);
                  window.location.replace(returnUrl);
                  // 3. Show the buyer a confirmation message.
                });
            },
            onCancel: function (data) {
              console.log("----------------CANCEL-----------------");
              let url = data['cancelUrl'].split('?')[0];
              console.log(url);
              return fetch(url,{ 
                headers:{
                  'Accept': 'application/json',
                  'Content-Type':'application/json',
                }
              })
                .then(function(res) {
                  console.log(res);
                  window.location.replace(returnUrl);
                  // 3. Show the buyer a confirmation message.
                });
            },
            onError: function (data) {
              console.log("----------------ERROR-----------------");
              let url = data['cancelUrl'].split('?')[0];
              let errorUrl = url.replace('cancel', 'error');
              return fetch(errorUrl,{ 
                headers:{
                  'Accept': 'application/json',
                  'Content-Type':'application/json',
                }
              })
                .then(function(res) {
                  console.log(res);
                  window.location.replace(returnUrl);
                  // 3. Show the buyer a confirmation message.
                });
            }
          },'#paypal');
        },
        error => {
          console.log("Error");
        }
      )
  }
}


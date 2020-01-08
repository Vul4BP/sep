import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { KpService } from 'src/services/kp.service';

declare var paypal;


@Component({
  selector: 'app-paypal-component',
  templateUrl: './paypal-component.component.html',
  styleUrls: ['./paypal-component.component.scss']
})
export class PaypalComponentComponent implements OnInit {
  @ViewChild('paypal', { static: true }) paypalElement: ElementRef;

  kpService: KpService;
  cancelUrl: String = "";

  constructor(kpService: KpService) {
    this.kpService = kpService;
  }

  ngOnInit() {
  
    paypal.Button.render({
      env: 'sandbox', // Or 'production'
      // Set up the payment:
      // 1. Add a payment callback
      payment: function(data, actions) {
        console.log(data);
        // 2. Make a request to your server
        return fetch('https://localhost:8443/paypalservice/api/paypal',{
          method:'post',
          body:JSON.stringify({
            casopisID: "ff8081816f3a3693016f3a3f3a950000",
            email:'voste69-facilitator@gmail.com',
            redirectUrl:'https://localhost:5004'
          }),
          headers:{
            'Accept': 'application/json',
            'Content-Type':'application/json'
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
        return fetch('https://localhost:8443/paypalservice/api/paypal/execute',{ 
          method:'post',
          body:JSON.stringify({
              paymentID: data.paymentID,
              payerID:   data.payerID
          }),
          headers:{
            'Accept': 'application/json',
            'Content-Type':'application/json'
          }
        })
          .then(function(res) {
            //window.location.replace("https://localhost:5004");
            // 3. Show the buyer a confirmation message.
          });
      },
      onCancel: function (data) {
        console.log("----------------CANCEL-----------------");
        //console.log(data);
        // Show a cancel page, or return to ca\\'rt
        //let url = data['cancelUrl'];
        console.log(data['cancelUrl']);
        //let url = data['cancelUrl'].split('?')[0];
        let url = 'https://localhost:8433/paypalservice/api/paypal/cancel/59';
        console.log(url);
        return fetch(url ,{ 
        
          //return fetch('https://localhost:8433/paypalservice/api/paypal/cancel/51?token=EC-1MY47322AW311710A' ,{
        //https://localhost:8443/paypalservice/api/paypal/cancel/43?token=EC-45A64448B9716913D
        //https://localhost:8433/paypalservice/api/paypal/cancel/51?token=EC-1MY47322AW311710A
        //return fetch('http://localhost:32891/api/paypal/cancel/43?token=EC-45A64448B9716913D' ,{

        method:'get'
        })
          .then(function(res){
            console.log('prosao cancel');
          });
      }
    },'#paypal');
  }
}

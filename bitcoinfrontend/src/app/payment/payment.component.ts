import { Component, OnInit } from '@angular/core';
import { KpService } from 'src/services/kp.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent {
  amountUsd: string;
  itemName: string;
  email: string;
  paymentUrl = "";
  itemId = ""

  constructor(private router: Router, private route: ActivatedRoute, private kpService: KpService) {
    this.route.params.subscribe(params => {
      this.itemId = params['id'];
    });
  }

  ngOnInit() {
    document.getElementById('magazineNameLbl').style.visibility = "hidden";
    document.getElementById('magazineName').style.visibility = "hidden";
    document.getElementById('magazinePriceLbl').style.visibility = "hidden";
    document.getElementById('magazinePrice').style.visibility = "hidden";
    document.getElementById('paymentImg').style.visibility = "hidden";

    this.kpService.getItem(this.itemId)
      .subscribe(
        resp => {
          console.log(resp);
          this.amountUsd = resp['amount'];
          this.email = resp['seller']['email'];
          this.itemName = resp['name'];

          if(this.email != null && this.amountUsd != null){
            this.getPaymentLink();
          }
        },
        err => {
          console.log(err);
        }
      );
  }

  getPaymentLink(){
    this.kpService.startTransaction(this.email, this.amountUsd)  
        .subscribe(data => {
          console.log(data);
          this.paymentUrl = data['paymentUrl'];

          let loadImg = document.getElementById('loadImg');
          loadImg.style.visibility = "hidden";
          loadImg.remove();
          
          let payImg = document.getElementById('paymentImg');
          let magName = document.getElementById('magazineName');
          let magNameLbl = document.getElementById('magazineNameLbl');
          let magPrice = document.getElementById('magazinePrice');
          let magPriceLbl = document.getElementById('magazinePriceLbl');

          magName.innerHTML = this.itemName;
          magPrice.innerHTML = this.amountUsd + " USD";
          magNameLbl.innerHTML = "Magazine Name: ";
          magPriceLbl.innerHTML = "Magazine Price: ";

          payImg.style.visibility = "visible";
          magNameLbl.style.visibility = "visible";
          magName.style.visibility = "visible";
          magPriceLbl.style.visibility = "visible";
          magPrice.style.visibility = "visible";

        });
  }

  goToPaymentUrl(){
    window.location.href = this.paymentUrl;
  }
}

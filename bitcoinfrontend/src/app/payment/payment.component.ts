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
  magazineId: string;
  magazineName: string;
  paymentUrl = "";

  constructor(private router: Router, private route: ActivatedRoute, private kpService: KpService) {
    this.route.params.subscribe(params => {
      this.magazineId = params['id'];
    });
  }

  ngOnInit() {
    document.getElementById('magazineNameLbl').style.visibility = "hidden";
    document.getElementById('magazineName').style.visibility = "hidden";
    document.getElementById('magazinePriceLbl').style.visibility = "hidden";
    document.getElementById('magazinePrice').style.visibility = "hidden";
    document.getElementById('paymentImg').style.visibility = "hidden";

    this.kpService.getMagazine(this.magazineId)
      .subscribe(data => {
        this.amountUsd = data['clanarina'];
        this.magazineId = data['casopisId'];
        this.magazineName = data['naziv'];

        if(this.magazineId != null && this.amountUsd != null){
          this.getPaymentLink();
        }

    })
  }

  getPaymentLink(){
    this.kpService.startTransaction(this.magazineId, this.amountUsd)  
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

          magName.innerHTML = this.magazineName;
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

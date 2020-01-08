import { Component } from '@angular/core';
import { KpService } from 'src/services/kp.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'paypalfrontend';
  id = '';
  urlString: string;
  url: URL;
  amountUsd: string;
  magazineId: string;
  magazineName: string;
  paymentUrl = "";

  constructor(private kpService: KpService) {
    this.urlString = window.location.href;
    this.url = new URL(this.urlString);
    this.id = this.url.searchParams.get('id');
  }

  ngOnInit() {
    document.getElementById('magazineNameLbl').style.visibility = "hidden";
    document.getElementById('magazineName').style.visibility = "hidden";
    document.getElementById('magazinePriceLbl').style.visibility = "hidden";
    document.getElementById('magazinePrice').style.visibility = "hidden";
    document.getElementById('paymentImg').style.visibility = "hidden";

    this.kpService.getMagazine(this.id)
      .subscribe(data => {
        this.amountUsd = data['price'];
        this.magazineId = data['id'];
        this.magazineName = data['name'];

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
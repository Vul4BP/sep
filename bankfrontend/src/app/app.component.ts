import { Component } from '@angular/core';
import { KpService } from 'src/services/kp.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'bankfrontend';
  isSubmitted = false;
  paymentUrl: String = "";
  id: string;
  urlString: string;
  url: URL;
  amountUsd: string;

  constructor(private kpService: KpService) {
    this.urlString = window.location.href;
    this.url = new URL(this.urlString);
    this.id = this.url.searchParams.get('id');
  }

  ngOnInit() {
    this.kpService.getPrice(this.id)
      .subscribe(data => {
        this.amountUsd = data['price'];
    })
  }

  submitForm(form: NgForm) {
    this.isSubmitted = true;
    if (!form.valid) {
      return false;
    } else {
      //this.kpService.startTransaction(this.id, this.amountUsd)  
      this.kpService.startTransaction(form.value) 
        .subscribe(data => {
          console.log(data);
          this.paymentUrl = data['paymentUrl'];
          document.getElementById('link').setAttribute('href',this.paymentUrl.toString());
          document.getElementById('link').innerHTML = this.paymentUrl.toString();
        });
    }
  }
}

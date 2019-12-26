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
  isSubmitted = false;
  paymentUrl: String = "";

  constructor(private kpService: KpService) {

  }

  submitForm(form: NgForm) {
    this.isSubmitted = true;
    if (!form.valid) {
      return false;
    } else {
      let email = form.controls['email'].value;
      let amount = form.controls['amount'].value;
      let redirectUrl = "https://localhost:5004";
      this.kpService.startTransaction(email, amount, redirectUrl)  
        .subscribe(data => {
          console.log(data);
          this.paymentUrl = data['paymentUrl'];
          document.getElementById('link').setAttribute('href',this.paymentUrl.toString());
          document.getElementById('link').innerHTML = this.paymentUrl.toString();
        });
    }
  }
}
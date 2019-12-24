import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import {KpService} from '../services/kp.service';
import {Payment} from "../models/payment";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'kpfrontend';
  isSubmitted = false;
  id = '';
  price = '';
  urlString: string;
  url: URL;
  payments = new  Array<Payment>();


  constructor(private kpService: KpService) {

    this.urlString = window.location.href;
    this.url = new URL(this.urlString);
    this.id = this.url.searchParams.get('id');
    this.price = this.url.searchParams.get('price');
    console.log(this.id);

  }

  ngOnInit() {
    this.kpService.getPayments(this.id).subscribe(data => {
      this.payments = data;
    });
  }

  submitForm(form: NgForm) {
    this.isSubmitted = true;
    if (!form.valid) {
      return false;
    } else {
      this.kpService.postPaymentMethod(form.value)
        .subscribe(data => {
          console.log(data);
          let route = '';
          if (data.result == 'bank') {
            route = 'https://localhost:5002';
          } else if (data.result == 'paypal') {
            route = 'https://localhost:5003';
          } else if (data.result == 'bitcoin') {
            route = 'https://localhost:5001';
          }

          console.log(route);
          window.location.replace(route);
        });
    }
  }
}

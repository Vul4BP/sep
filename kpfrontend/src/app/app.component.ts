import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import {KpService} from '../services/kp.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'kpfrontend';
  isSubmitted = false;

  constructor(private kpService: KpService) {
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
          }
          else if (data.result == 'paypal') {
            route = 'https://localhost:5003';
 }
          else if (data.result == 'bitcoin') {
            route = 'https://localhost:5001';
 }

          console.log(route);
          window.location.replace(route);
        });
    }
  }
}

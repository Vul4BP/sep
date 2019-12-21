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
        .subscribe(data => alert(data));
    }
  }
}

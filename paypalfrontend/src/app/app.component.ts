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

  constructor(private kpService: KpService) {
  }

  submitForm(form: NgForm) {
    this.isSubmitted = true;
    if (!form.valid) {
      return false;
    } else {
      this.kpService.startTransaction(form.value)  
        .subscribe(data => {
          console.log(data);
        });
    }
  }
}
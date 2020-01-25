import { Component } from '@angular/core';
import { KpService } from 'src/services/kp.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
 
  constructor(private kpService: KpService) {
  }

  ngOnInit() {
  }
}

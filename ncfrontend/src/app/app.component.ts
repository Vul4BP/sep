import { Component } from '@angular/core';
import {NcService} from '../services/nc.service';
import {Magazine} from '../models/magazine';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'ncfrontend';
  magazines = new  Array<Magazine>();

  constructor(private ncServices: NcService) {
  }

  // tslint:disable-next-line:use-lifecycle-interface
  ngOnInit() {
    this.ncServices.getData().subscribe(data => {
      this.magazines = data;
    });
  }

  onBuy(m) {
    this.ncServices.postMagazine(m).subscribe(data => {
      //window.location.replace('https://localhost:5000/magazine?id=' + m.id + '&price=' + m.price);
      window.location.replace(data['redirectUrl']);
    });
  }
}

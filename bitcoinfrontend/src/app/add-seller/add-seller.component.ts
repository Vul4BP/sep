import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { KpService } from 'src/services/kp.service';

@Component({
  selector: 'app-add-seller',
  templateUrl: './add-seller.component.html',
  styleUrls: ['./add-seller.component.css']
})
export class AddSellerComponent implements OnInit {

  private sellerId = "";
  private email = "";

  constructor(private router: Router, private route: ActivatedRoute, private kpService: KpService) { 
    this.route.params.subscribe(params => {
      this.sellerId = params['id'];
    });
  }

  ngOnInit() {
    console.log(this.sellerId);
    this.kpService.getSeller(this.sellerId)
    .subscribe(
      res => {
        this.email = res['email'];
      },
      err => {
        console.log(err);
      });
  }

  onSubmit(value, form){
    let body = value;
    body['email'] = this.email;
    console.log(body);

    this.kpService.addSeller(body)
      .subscribe(data => {
        //console.log(data);
        window.location.replace("https://localhost:5004/potvrdinacineplacanja");
        
      });
  }

}

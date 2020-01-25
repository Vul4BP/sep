import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { KpService } from 'src/services/kp.service';

@Component({
  selector: 'app-add-seller',
  templateUrl: './add-seller.component.html',
  styleUrls: ['./add-seller.component.css']
})
export class AddSellerComponent implements OnInit {

  private magazineId = "";

  constructor(private router: Router, private route: ActivatedRoute, private kpService: KpService) { 
    this.route.params.subscribe(params => {
      this.magazineId = params['id'];
    });
  }

  ngOnInit() {
  }

  onSubmit(value, form){
    let body = value;
    body['magazineId'] = this.magazineId;
    console.log(body);

    this.kpService.addSeller(body)
      .subscribe(data => {
        //console.log(data);
        window.location.replace("https://localhost:5004/potvrdinacineplacanja");
        
      });
  }

}

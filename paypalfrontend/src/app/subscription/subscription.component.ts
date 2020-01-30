import { Component, OnInit } from '@angular/core';
import {KpService} from "../../services/kp.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CreatePlanRequest} from "../models/CreatePlanRequest";

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css']
})
export class SubscriptionComponent implements OnInit {

  private itemId = "";
  private price: number;
  private email: string;
  planId: string;
  plan: string;
  itemName: string;
  planRequest: CreatePlanRequest;

  constructor(private kpService: KpService, private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe(params => {
      this.itemId = params['id'];
    });
  }

  ngOnInit() {

    this.kpService.getItem(this.itemId)
      .subscribe(
        resp => {
          console.log(resp);
          this.price = resp['amount'];
          this.email = resp['seller']['email'];
          this.itemName = resp['name'];
        },
        error => {
          console.log("Error");
        });
  }

  startSubscription() {
    console.log('START');
    this.kpService.createAndActivatePlan(this.planRequest).subscribe(data => {
      this.planId = data.id;

      let obj = {};
      obj["email"] = this.email;
      obj["planId"] = this.planId;

      console.log('ID ->' + this.planId);
      this.kpService.subscribeToPlan(obj).subscribe( data1 => {
        console.log(data1.url);
        window.location.replace(data1.url);
      }, error => {});
    }, error => {
      console.log(error);
    });
  }

  onChange(value: any) {
    this.plan = value.target.value;
    if (this.plan === 'monthly') {
      this.planRequest = new CreatePlanRequest(this.price, 'USD', this.itemName, 'Neki opis', 'REGULAR', 'MONTH', 1, 12 );
    }
    console.log(this.planRequest);
  }
}

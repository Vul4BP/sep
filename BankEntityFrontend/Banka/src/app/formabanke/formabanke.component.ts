import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { BankService } from 'src/service/bank.service';


@Component({
  selector: 'app-formabanke',
  templateUrl: './formabanke.component.html',
  styleUrls: ['./formabanke.component.css']
})
export class FormabankeComponent implements OnInit {
  title = 'bankaentityfrontend';
  isSubmitted = false;
  transaction="";

  constructor(private bankservice: BankService,private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.transaction = this.activatedRoute.snapshot.paramMap.get('transaction');
    console.log(this.transaction);
  }

  submitForm(form: NgForm) {
    this.isSubmitted = true;
    if (!form.valid) {
      return false;
    } else {
      this.bankservice.confirmed(form.value, this.transaction)  
        .subscribe(data => {
          console.log(data);
          let url = data['url'] + data['id'];
          this.bankservice.changeStatus(url).subscribe(data => {
            console.log(data);
            //redirektovati
            let redirectUrl = data['redirectUrl'];
            window.location.href = redirectUrl;
            
          });
        },
        error => {
          console.log(console.error);
        });
    }
  }

}

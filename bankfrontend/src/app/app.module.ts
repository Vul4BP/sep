import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { KpService } from '../services/kp.service';
import { PaymentComponent } from './payment/payment.component';
import { AddSellerComponent } from './add-seller/add-seller.component';

const Routes = [
  {
    path: "payment/:id",
    component: PaymentComponent,
  },
  {
    path: "seller/:id",
    component: AddSellerComponent,
  }
]

@NgModule({
  declarations: [
    AppComponent,
    PaymentComponent,
    AddSellerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(Routes),
    HttpClientModule
  ],
  providers: [KpService],
  bootstrap: [AppComponent]
})
export class AppModule { }

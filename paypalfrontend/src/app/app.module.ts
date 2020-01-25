import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { KpService } from 'src/services/kp.service';
import { HttpClientModule } from '@angular/common/http';
import { AddSellerComponent } from './add-seller/add-seller.component';
import { RouterModule } from '@angular/router';
import { PaymentComponent } from './payment/payment.component';

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

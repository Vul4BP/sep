import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormabankeComponent } from './formabanke/formabanke.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { FormsModule } from '@angular/forms';
import { BankService } from 'src/service/bank.service';

@NgModule({
  declarations: [
    AppComponent,
    FormabankeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [BankService],
  bootstrap: [AppComponent]
})
export class AppModule { }

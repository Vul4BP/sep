import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

// @ts-ignore
@Injectable({
  providedIn: 'root'
})

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable()
export class KpService {

  constructor(private httpClient: HttpClient) { }

  postPaymentMethod(paymentMethod: string, magid: string): Observable<any> {
    let payment = paymentMethod['payment'];
    let obj = {};
    obj['payment'] = payment;
    obj['id'] = magid;
    return this.httpClient.post('https://localhost:8443/sellerservice/paymentmethod', JSON.stringify(obj) );
  }

  getPayments(magazineId: string): Observable<any> {
    return this.httpClient.get('https://localhost:8443/sellerservice/getpayments?magid=' + magazineId);
  }
}

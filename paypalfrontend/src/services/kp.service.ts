import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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

  postPaymentMethod(paymentMethod: string): Observable<any> {
    return this.httpClient.post('https://localhost:8443/paypalservice/payment/pay', "elena");
  }

  startTransaction(formData: string): Observable<any> {
    return this.httpClient.get('https://localhost:8443/paypalservice/getjson');


  }
}

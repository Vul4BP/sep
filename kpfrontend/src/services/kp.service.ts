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

  postPaymentMethod(data: Object): Observable<any> {
    return this.httpClient.post('https://localhost:8443/sellerservice/item/paymentmethod', data);
  }

  getPayments(magazineId: string): Observable<any> {
    return this.httpClient.get('https://localhost:8443/sellerservice/item/getpayments/' + magazineId);
  }
}

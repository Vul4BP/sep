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

  startTransaction(email: string, amount: string): Observable<any> {
    return this.httpClient.post('https://localhost:8443/bitcoinservice/createPayment',"email=" + email + "&amount=" + amount, {headers: new HttpHeaders().set('Content-Type','application/x-www-form-urlencoded')});
  }

  getSeller(id : Object): Observable<any>{
    return this.httpClient.get(`https://localhost:8443/sellerservice/seller/get/${id}`);
  }

  getItem(id : String): Observable<any>{
    return this.httpClient.get(`https://localhost:8443/sellerservice/item/get/${id}`);
  }

  addSeller(seller: Object): Observable<any>{
    return this.httpClient.post('https://localhost:8443/bitcoinservice/seller/add', seller); 
  }
}
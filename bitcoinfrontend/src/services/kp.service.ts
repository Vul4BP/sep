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

  startTransaction(email: string, amount: string, redirectUrl: string): Observable<any> {
    return this.httpClient.post('https://localhost:8443/bitcoinservice/preparePayment',"email=" + email + "&amount=" + amount + "&redirectUrl=" + redirectUrl, {headers: new HttpHeaders().set('Content-Type','application/x-www-form-urlencoded')});
  }
}
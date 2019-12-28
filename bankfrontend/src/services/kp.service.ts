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

  /*
  startTransaction(id: string, amountUsd: string): Observable<any> {
    let obj = {};
    obj['amount'] = amountUsd;
    obj['magazineId'] = id; 
    return this.httpClient.post('https://localhost:8443/bankservice/payment',obj);
  }*/

  startTransaction(formdata: String): Observable<any> {
    return this.httpClient.post('https://localhost:8443/bankservice/payment',formdata);
  }

  getPrice(magId: string): Observable<any>{
    return this.httpClient.get('https://localhost:8443/sellerservice/getmagazine?magid=' + magId);
  }
}

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

  startTransaction(formdata: Object): Observable<any> {
    return this.httpClient.post('https://localhost:8443/bankservice',formdata);
  }

  addSeller(seller: Object): Observable<any>{
    return this.httpClient.post('https://localhost:8443/bankservice/seller/add', seller); 
  }

  getSeller(id : Object): Observable<any>{
    return this.httpClient.get(`https://localhost:8443/sellerservice/seller/get/${id}`);
  }

  getItem(id : String): Observable<any>{
    return this.httpClient.get(`https://localhost:8443/sellerservice/item/get/${id}`);
  }
}

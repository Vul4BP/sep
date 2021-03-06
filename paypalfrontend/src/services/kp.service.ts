import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {CreatePlanRequest} from "../app/models/CreatePlanRequest";

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

  postData(formdata: string): Observable<any> {
    return this.httpClient.post('https://localhost:8443/paypalservice/createPayment', formdata);
  }

  addSeller(seller: Object): Observable<any>{
    return this.httpClient.post('https://localhost:8443/paypalservice/seller/add', seller);
  }

  getSeller(id : String): Observable<any>{
    return this.httpClient.get(`https://localhost:8443/sellerservice/seller/get/${id}`);
  }

  getItem(id : String): Observable<any>{
    return this.httpClient.get(`https://localhost:8443/sellerservice/item/get/${id}`);
  }

  createAndActivatePlan(request: CreatePlanRequest): Observable<any> {
    return this.httpClient.post('https://localhost:8443/paypalservice/subscription/plan/createAndActivePlan', request);
  }

  subscribeToPlan(obj: object): Observable<any> {
    return this.httpClient.post('https://localhost:8443/paypalservice/subscription/plan/subscribe/' , obj);
  }
}

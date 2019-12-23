import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Magazine} from '../models/magazine';


@Injectable({
  providedIn: 'root'
})


export class NcService {

  constructor(private httpClient: HttpClient) { }

  getData( ): Observable<any> {
    return this.httpClient.get('http://localhost:5005/getdata');
  }

  postMagazine(magazine: Magazine): Observable<any> {
    return this.httpClient.post('http://localhost:5005/postmagazine', magazine);
  }
}

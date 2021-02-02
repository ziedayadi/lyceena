import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';


const BACK_END_SERVICE_NAME = '/ref';

@Injectable({
  providedIn: 'root'
})
export class RefService {

  constructor(private httpClient : HttpClient) { }

  public findDays() : Observable<any> {
    return this.httpClient.get( BASE_URL + BACK_END_SERVICE_NAME + '/days');
  }

  public findHours() : Observable<any> {
    return this.httpClient.get( BASE_URL + BACK_END_SERVICE_NAME + '/hours');
  }

  public findGlobalRef() : Observable<any> {
    return this.httpClient.get( BASE_URL + BACK_END_SERVICE_NAME + '/global-refs');
  }
}

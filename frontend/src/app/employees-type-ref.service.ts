import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from './constants';


const BACK_END_SERVICE_NAME = '/ref/employees-type';

@Injectable({
  providedIn: 'root'
})
export class EmployeesTypeRefService {

  
  constructor(private httpClient : HttpClient) { }
  

  public findAll() : Observable<any> {
    return this.httpClient.get( BASE_URL + BACK_END_SERVICE_NAME);
  }
}

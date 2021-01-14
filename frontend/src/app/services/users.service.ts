import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';

const BACK_END_SERVICE_NAME = '/users';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private httpClient : HttpClient) { }

  public findCurrentUserDetails() : Observable<any>{
    return this.httpClient.get( BASE_URL +BACK_END_SERVICE_NAME+'/'); 
  }

}

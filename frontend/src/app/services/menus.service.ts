import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';

const BACK_END_SERVICE_NAME = '/menus/';


@Injectable({
  providedIn: 'root'
})
export class MenusService {

  constructor(private httpClient : HttpClient) { }

  public findMenus() : Observable<any>{
      return this.httpClient.get(BASE_URL + BACK_END_SERVICE_NAME)
  }
}

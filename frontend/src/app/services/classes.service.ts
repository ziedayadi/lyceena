import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';


const BACK_END_SERVICE_NAME = '/classes/';
@Injectable({
  providedIn: 'root'
})
export class ClassesService {

  constructor(private httpClient : HttpClient) {
   }

   public findAll() : Observable<any> {
    return this.httpClient.get( BASE_URL + BACK_END_SERVICE_NAME);
  }

  public findOne(id) : Observable<any> {
    return this.httpClient.get( BASE_URL + BACK_END_SERVICE_NAME + '/' + id);
  }
  
  public save (parent) : Observable<any> {
    return this.httpClient.post( BASE_URL + BACK_END_SERVICE_NAME, parent);
  }

  public remove(id : Number) : Observable<any> {
    return this.httpClient.delete( BASE_URL +BACK_END_SERVICE_NAME+id);
  }
}

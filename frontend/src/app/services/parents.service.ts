import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class ParentsService {

  constructor(private httpClient : HttpClient) { }

  public findAll() : Observable<any>{
    return this.httpClient.get( BASE_URL +'/parents/');
  }

  public save (parent) : Observable<any> {
    return this.httpClient.post( BASE_URL +'/parents/', parent);
  }

  public remove(id : String) : Observable<any> {
    return this.httpClient.delete( BASE_URL +'/parents/'+id);
  }
}

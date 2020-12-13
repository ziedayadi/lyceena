import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class ClassesService {

  constructor(private httpClient : HttpClient) {
   }

   public findAll() : Observable<any> {
    return this.httpClient.get( BASE_URL +'/classes/');
  }
}

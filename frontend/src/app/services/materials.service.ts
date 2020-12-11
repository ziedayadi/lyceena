import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import {BASE_URL} from '../constants'



@Injectable({
  providedIn: 'root'
})
export class MaterialsService {

  constructor(private httpClient : HttpClient) { }

  public findAll() : Observable<any> {
    return this.httpClient.get( BASE_URL +'/ref/materials/');
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import {BASE_URL} from '../constants'

const BACK_END_SERVICE_NAME = '/ref/materials/';


@Injectable({
  providedIn: 'root'
})
export class MaterialsService {

  constructor(private httpClient : HttpClient) { }
  

  public findAll() : Observable<any> {
    return this.httpClient.get( BASE_URL + BACK_END_SERVICE_NAME);
  }

  public save (material) : Observable<any> {
    return this.httpClient.post( BASE_URL + BACK_END_SERVICE_NAME, material);
  }

  public remove(id : String) : Observable<any> {
    return this.httpClient.delete( BASE_URL + BACK_END_SERVICE_NAME+id); 
  }

  public findTeachersForMaterial(materialId : String) : Observable<any> {
    return this.httpClient.get( BASE_URL + BACK_END_SERVICE_NAME+ materialId +'/teachers'); 
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';


const BACK_END_SERVICE_NAME = '/class-level-ref/';

@Injectable({
  providedIn: 'root'
})
export class ClassLevelsRefService {

  constructor(private httpClient : HttpClient) { }

  public findAll() : Observable<any>{
    return this.httpClient.get( BASE_URL +BACK_END_SERVICE_NAME);
  }

  public findById(levelId) : Observable<any>{
    return this.httpClient.get( BASE_URL +BACK_END_SERVICE_NAME + levelId); 
  }

  public findMaterialsByClassLevelId(levelId) : Observable<any>{
    return this.httpClient.get( BASE_URL +BACK_END_SERVICE_NAME + levelId + '/materials');  
  }

  public removeMaterialFromClassLevel(levelId, materialId){
    return this.httpClient.delete( BASE_URL +BACK_END_SERVICE_NAME+levelId+'/'+materialId);
  }


  public save (parent) : Observable<any> {
    return this.httpClient.post( BASE_URL + BACK_END_SERVICE_NAME, parent);
  }

  public remove(id : Number) : Observable<any> {
    return this.httpClient.delete( BASE_URL +BACK_END_SERVICE_NAME+id);
  }

  public updateOrSaveMaterialToLevel(request){
    return this.httpClient.post( BASE_URL + BACK_END_SERVICE_NAME +request.levelId + '/save_material', request);
  }
}

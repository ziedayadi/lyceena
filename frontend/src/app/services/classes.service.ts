import { HttpClient, HttpParams } from '@angular/common/http';
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
    return this.httpClient.get( BASE_URL + BACK_END_SERVICE_NAME + id);
  }
  
  public save (parent) : Observable<any> {
    return this.httpClient.post( BASE_URL + BACK_END_SERVICE_NAME, parent);
  }

  public remove(id : Number) : Observable<any> {
    return this.httpClient.delete( BASE_URL +BACK_END_SERVICE_NAME+id);
  }

  public findStudentsByClassId(classId) : Observable<any> {
    return this.httpClient.get( BASE_URL + BACK_END_SERVICE_NAME + classId + '/students');
  }

    
  findTeachersByClassId(classId: any) {
    return this.httpClient.get( BASE_URL + BACK_END_SERVICE_NAME + classId + '/teachers');
  }

  findSessionssByClassId(classId: any) {
    return this.httpClient.get( BASE_URL + BACK_END_SERVICE_NAME + classId + '/sessions');
  }

  createTimeSheet(classId: any) {
    return this.httpClient.post( BASE_URL + BACK_END_SERVICE_NAME + 'create-time-sheet?classId='+ classId, null);
  }

  findFreeHoursByClassIdAndTeacherId(classId, teacherId){
    let params = new HttpParams().set('classId', classId).set('teacherId', teacherId);
    return this.httpClient.get( BASE_URL + BACK_END_SERVICE_NAME + 'free-hours', {
      params : params
    });
  }

  updateSession(updateSession){
    return this.httpClient.post( BASE_URL + BACK_END_SERVICE_NAME + 'sessions', updateSession);
  }

}

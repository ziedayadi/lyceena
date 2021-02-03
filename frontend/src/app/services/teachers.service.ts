import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class TeachersService {

  constructor(private httpClient : HttpClient) { }

  public findAll() : Observable<any>{
    return this.httpClient.get( BASE_URL +'/teachers/');
  }

  public save(teacher) : Observable<any> {
    return this.httpClient.post( BASE_URL +'/teachers/', teacher);
  }

  public remove(id : String) : Observable<any> {
    return this.httpClient.delete( BASE_URL +'/teachers/'+id);
  }

  public timesheet() : Observable<any> {
    return this.httpClient.get( BASE_URL +'/teachers/timesheet');
  }

  public findOne(id : String) : Observable<any> {
    return this.httpClient.get( BASE_URL +'/teachers/'+id);
  }

  public findClasses(id : String) : Observable<any> {
    return this.httpClient.get( BASE_URL +'/teachers/'+id+'/classes');
  }
}

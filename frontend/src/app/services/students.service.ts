import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class StudentsService {

  constructor(private httpClient : HttpClient) { }

  public findAll() : Observable<any> {
    return this.httpClient.get( BASE_URL +'/students/');
  }

  public save(student) : Observable<any> {
    return this.httpClient.post( BASE_URL +'/students/', student);
  }

  public remove(id : String) : Observable<any> {
    return this.httpClient.delete( BASE_URL +'/students/'+id);
  }

  public findByParentId(parentId : String) : Observable<any>  {
    return this.httpClient.get( BASE_URL +'/students/parent/'+parentId);
  }

  public findOneById(id : String) : Observable<any>  {
    return this.httpClient.get( BASE_URL +'/students/'+id);
  }

  public findOneByUsername(username : String) : Observable<any>  {
    return this.httpClient.get( BASE_URL +'/students/username/'+username);
  }
}

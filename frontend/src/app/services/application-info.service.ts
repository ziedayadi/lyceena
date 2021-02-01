import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from '../constants';


const BACK_END_SERVICE_NAME = '/actuator';


@Injectable({
  providedIn: 'root'
})
export class ApplicationInfoService {



  constructor(private httpClient : HttpClient) { }

  getInfo(){
    return this.httpClient.get(BASE_URL + BACK_END_SERVICE_NAME + '/info')
  }

  getHealth(){
    return this.httpClient.get(BASE_URL + BACK_END_SERVICE_NAME + '/health')
  }

  getEnv(){
    return this.httpClient.get(BASE_URL + BACK_END_SERVICE_NAME + '/env')
  }

  getHttpTraces(){
    return this.httpClient.get(BASE_URL + BACK_END_SERVICE_NAME + '/httptrace')
  }
}

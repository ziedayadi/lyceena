import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {




  constructor(private httpClient: HttpClient) { }

  upload(file: File){
    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', BASE_URL + '/files/upload', formData, {
          reportProgress: true,
          responseType: 'json'
        });
    return  this.httpClient.request(req);
  }
}

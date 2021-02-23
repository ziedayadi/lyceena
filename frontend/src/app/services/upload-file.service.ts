import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from '../constants';

const BACK_END_SERVICE_NAME = '/files';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {

  constructor(private httpClient: HttpClient) { }

  upload(file: File, sessionId){
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('sessionId', sessionId);
    const req = new HttpRequest('POST', BASE_URL + BACK_END_SERVICE_NAME + '/upload', formData, {
          reportProgress: true,
          responseType: 'json'
        });
    return  this.httpClient.request(req);
  }

  getFilesBySessionId(sessionId){
    return this.httpClient.get(BASE_URL + BACK_END_SERVICE_NAME + '/session/' + sessionId)
  }

  donwnloadFile(fileId, fileType){
    return this.httpClient.get(BASE_URL + BACK_END_SERVICE_NAME + '/file/' + fileId, { responseType :'arraybuffer' })
  }
}

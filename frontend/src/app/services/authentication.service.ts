import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cookie } from 'ng2-cookies';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public clientId = 'lyceena-client';
  public redirectUri = 'http://localhost:4200';
  private clientSecret = 'c8b3aac2-8064-481d-9e6d-004b1f4bcd15'; 


  constructor(private _http: HttpClient) { }

  retrieveToken(code) {
    let params = new URLSearchParams();   
    params.append('grant_type','authorization_code');
    params.append('client_id', this.clientId);
    params.append('client_secret', this.clientSecret);
    params.append('redirect_uri', this.redirectUri);
    params.append('code',code);
    let headers = 
      new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8' });
       
      this._http.post('http://localhost:8083/auth/realms/lyceena/protocol/openid-connect/token', 
        params.toString(), { headers: headers })
        .subscribe(
          data => this.saveToken(data),
          err => alert('Invalid Credentials')); 
  }

  saveToken(token) {
    var expireDate = new Date().getTime() + (1000 * token.expires_in);
    Cookie.set("access_token", token.access_token, expireDate);
    window.location.href = 'http://localhost:4200/';
  }

  checkCredentials() {
    return Cookie.check('access_token') && !this.tokenExpired();
  } 

  logout() {
    Cookie.delete('access_token');
    window.location.reload();
    
  }
  getCurrentUserInfo(){
    let currentUserInfo =  JSON.parse(atob(Cookie.get('access_token').split('.')[1]))
    return currentUserInfo;
  }

  private tokenExpired() {
    const token = Cookie.get('access_token'); 
    const expiry = (JSON.parse(atob(token.split('.')[1]))).exp;
    
    let expired =  (Math.floor((new Date).getTime() / 1000)) >= expiry;
    return expired;
  }
}

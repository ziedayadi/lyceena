import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit  {

  constructor(private authenticationService: AuthenticationService) { }


  public isLoggedIn = false;
  title = 'Lyceena';

  ngOnInit(): void {    
    this.isLoggedIn = this.authenticationService.checkCredentials();    
    let i = window.location.href.indexOf('code');

    if(!this.isLoggedIn && i != -1) {
      this.authenticationService.retrieveToken(window.location.href.substring(i + 5));
    }
  }

  login(){
    window.location.href =  
    'http://localhost:8083/auth/realms/lyceena/protocol/openid-connect/auth?response_type=code&client_id=' +  this.authenticationService.clientId + '&redirect_uri='+ this.authenticationService.redirectUri;
  }

  logout() {
    this.authenticationService.logout();
  }

}

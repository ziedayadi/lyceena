import { AfterViewInit, Component, OnInit, Renderer2 } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoadingDialogComponent } from './components/utils/loading-dialog/loading-dialog.component';
import { AuthenticationService } from './services/authentication.service';
import { LoaderService } from './services/loader.service';
import { environment  } from "../environments/environment";

const BASE_URI = environment.apiUrl; 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit   {

  constructor(private authenticationService: AuthenticationService
    , private loaderService: LoaderService
    , private renderer: Renderer2
    , public dialog: MatDialog) { }


  public isLoggedIn = false;
  title = 'Lyceena';
  dialogRef : any ;

  ngOnInit(): void { 
    this.isLoggedIn = this.authenticationService.checkCredentials();    
    let i = window.location.href.indexOf('code');

    if(!this.isLoggedIn && i != -1) {
      this.authenticationService.retrieveToken(window.location.href.substring(i + 5));
    }
  }

  ngAfterViewInit() {
    this.loaderService.httpProgress().subscribe((isLoading: boolean) => {
       
      if (isLoading) {
        this.dialogRef = this.dialog.open(LoadingDialogComponent, {
          disableClose: true,
          data: {}
        });
      } else {
          this.dialogRef.close();      
      }
    });
  }

  login(){
    const oauth2Url = environment.oauth2Url; 
    const redirectUri = environment.redirectUri
    window.location.href =  
    oauth2Url + 
    '/auth/realms/lyceena/protocol/openid-connect/auth?response_type=code&client_id=' 
    +  this.authenticationService.clientId 
    + '&redirect_uri='
    + redirectUri;
  }

  logout() {
    this.authenticationService.logout();
  }

}

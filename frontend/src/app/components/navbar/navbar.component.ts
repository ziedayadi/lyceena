import { Component, OnInit } from '@angular/core';
import { Cookie } from 'ng2-cookies';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  currentUser;

  constructor(private authenticationService : AuthenticationService) { }

  ngOnInit(): void {
      this.currentUser = this.authenticationService.getCurrentUserInfo()
      console.log(this.currentUser.name)
  }

  logout() {
    this.authenticationService.logout();
  }

}

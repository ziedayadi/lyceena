import { Component, OnInit } from '@angular/core';
import { Cookie } from 'ng2-cookies';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { MenusService } from 'src/app/services/menus.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  currentUser;
  menus; 

  constructor(private authenticationService : AuthenticationService,
    private menusService : MenusService) { }

  ngOnInit(): void {
      this.currentUser = this.authenticationService.getCurrentUserInfo()
      this.fetchMenus();
  }

  private fetchMenus(){
    this.menusService.findMenus().subscribe(r=>{
      console.log(r);
      this.menus = r;
    })
  }

  logout() {
    this.authenticationService.logout();
  }

}

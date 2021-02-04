import { Component, OnInit } from '@angular/core';
import { ApplicationInfoService } from 'src/app/services/application-info.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { MenusService } from 'src/app/services/menus.service';
import { RefService } from 'src/app/services/ref.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  currentUser;
  menus; 
  buildInfo; 
  globalRef; 

  constructor(private authenticationService : AuthenticationService,
    private applicationInfoService : ApplicationInfoService,
    private refService : RefService,
    private menusService : MenusService) { }

  ngOnInit(): void {
      this.currentUser = this.authenticationService.getCurrentUserInfo()
      this.applicationInfoService.getInfo().subscribe(r=>this.buildInfo = r); 
      this.refService.findGlobalRef().subscribe(r=>this.globalRef = r);
      this.fetchMenus();
  }

  private fetchMenus(){
    this.menusService.findMenus().subscribe(r=>{
      this.menus = r;
    })
  }

  getSchoolName(){
    if(this.globalRef) {
      let schoolName = this.globalRef.filter(e=>e.code = 'SCHOOL_NAME')[0]
      return schoolName.value
    } else return 'Unknown school name'
  
  }

  logout() {
    this.authenticationService.logout();
  }

}

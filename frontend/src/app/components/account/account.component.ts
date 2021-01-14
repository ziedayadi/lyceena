import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {


  currentUser : any; 

  constructor(private userService : UsersService) { }

  ngOnInit(): void {
    this.userService.findCurrentUserDetails().subscribe(r=>{
      this.currentUser = r
      console.log(this.currentUser)
    }); 
  }

}

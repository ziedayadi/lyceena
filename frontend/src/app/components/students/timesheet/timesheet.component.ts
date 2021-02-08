import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { StudentsService } from 'src/app/services/students.service';

@Component({
  selector: 'app-student-timesheet',
  templateUrl: './timesheet.component.html',
  styleUrls: ['./timesheet.component.css']
})
export class StudentTimesheetComponent implements OnInit {
  currentUser: any;
  student : any;

  constructor(private authService: AuthenticationService,
    private studentsService : StudentsService) { }

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUserInfo();
    this.studentsService.findOneByUsername(this.currentUser.preferred_username).subscribe(r=>{this.student = r; console.log(r)});
  }

}

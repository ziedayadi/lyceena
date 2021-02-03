import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { RefService } from 'src/app/services/ref.service';
import { TeachersService } from 'src/app/services/teachers.service';

@Component({
  selector: 'app-teacher-time-sheet',
  templateUrl: './teacher-time-sheet.component.html',
  styleUrls: ['./teacher-time-sheet.component.css']
})
export class TeacherTimeSheetComponent implements OnInit {


  timesheet;
  currentUser: any;
  days;
  hours;

  constructor(private teachersService: TeachersService
    , private authService: AuthenticationService
    , private refService: RefService) { }

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUserInfo();
    this.refService.findDays().subscribe(r => this.days = r)
    this.refService.findHours().subscribe(r => this.hours = r)
    this.fetchTimesheet();

  }

  fetchTimesheet() {
    this.teachersService.timesheet().subscribe(r => {
      this.timesheet = r;
      console.log(this.timesheet)
    })
  }

  getSession(d, h) {
    let foundSession = this.timesheet.filter(ts => (ts.dayOfWeek.id == d.id && ts.startHour.id == h.id))[0]
    return foundSession;
  }
}

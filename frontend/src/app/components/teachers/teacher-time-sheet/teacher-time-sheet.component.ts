import { Component, OnInit } from '@angular/core';
import { TeachersService } from 'src/app/services/teachers.service';

@Component({
  selector: 'app-teacher-time-sheet',
  templateUrl: './teacher-time-sheet.component.html',
  styleUrls: ['./teacher-time-sheet.component.css']
})
export class TeacherTimeSheetComponent implements OnInit {


  timesheet;
  constructor(private teachersService : TeachersService) { }

  ngOnInit(): void {
    this.fetchTimesheet(); 
  }

  fetchTimesheet(){
    this.teachersService.timesheet().subscribe(r=>{
      this.timesheet = r;
    })
  }

}

import { Component, OnInit } from '@angular/core';
import { AttendanceService } from 'src/app/services/attendance.service';

@Component({
  selector: 'app-student-attendance-list',
  templateUrl: './student-attendance-list.component.html',
  styleUrls: ['./student-attendance-list.component.css']
})
export class StudentAttendanceListComponent implements OnInit {

  attendances; 
  
  constructor(private attendanceService : AttendanceService) { }

  ngOnInit(): void {
    this.fetchAttendance();
  }

  fetchAttendance(){
    this.attendanceService.getAttendanceForConnectedStudent().subscribe(r=>{
      this.attendances = r;
    })
  }

}

import { Component, OnInit } from '@angular/core';
import { AttendanceService } from 'src/app/services/attendance.service';

@Component({
  selector: 'app-current-session',
  templateUrl: './teacher-current-session.component.html',
  styleUrls: ['./teacher-current-session.component.css']
})
export class TeacherCurrentSessionComponent implements OnInit {

  session : any;

  constructor(private attendanceService : AttendanceService) { }

  ngOnInit(): void {
    this.attendanceService.getCurrentSessionForTeacher().subscribe(r=>this.session = r)
  }

}

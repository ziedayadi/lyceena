import { Component, OnInit } from '@angular/core';
import { AttendanceService } from 'src/app/services/attendance.service';

@Component({
  selector: 'app-session-list',
  templateUrl: './session-list.component.html',
  styleUrls: ['./session-list.component.css']
})
export class SessionListComponent implements OnInit {
  sessions: Object;

  constructor(private attendanceService : AttendanceService) { }

  ngOnInit(): void {
      this.fetchSessions();
  }

  private fetchSessions(){
    this.attendanceService.getTeacherSessions().subscribe(r=>this.sessions = r);
  }

}

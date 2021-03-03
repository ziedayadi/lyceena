import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AttendanceService } from 'src/app/services/attendance.service';

@Component({
  selector: 'app-student-session-details',
  templateUrl: './student-session-details.component.html',
  styleUrls: ['./student-session-details.component.css']
})
export class StudentSessionDetailsComponent implements OnInit {

  sessionDetails;

  constructor(private activatedRoute: ActivatedRoute,
    public attendanceService: AttendanceService) { }

  ngOnInit(): void {
    this.fetchSessionDetails();
  }
  private fetchSessionDetails() {
    const sessionId = this.activatedRoute.snapshot.paramMap.get('sessionId');
    this.attendanceService.getSessionForStudentBySessionId(sessionId).subscribe(r => this.sessionDetails = r)
  }

  getSessionPresence() {
    return {
      label: this.attendanceService.getStudentAttendanceTranslation(this.sessionDetails.students[0].presence),
      code: this.sessionDetails.students[0].presence
    }
  }

}

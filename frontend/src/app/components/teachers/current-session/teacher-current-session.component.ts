import { Component, OnInit } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { AttendanceService } from 'src/app/services/attendance.service';

@Component({
  selector: 'app-current-session',
  templateUrl: './teacher-current-session.component.html',
  styleUrls: ['./teacher-current-session.component.css']
})
export class TeacherCurrentSessionComponent implements OnInit {

  session: any;
  htmlContent: any;
  editorConfig: AngularEditorConfig = {
    editable: true,
    spellcheck: true,
    height: 'auto',
    minHeight: '430px',
  }
  constructor(public attendanceService: AttendanceService) { }

  ngOnInit(): void {
    this.attendanceService.getCurrentSessionForTeacher().subscribe(r => this.session = r)
  }

  onChange($event, student) {
    let request = {
      sessionAttendanceId: this.session.id,
      studentId: student.id,
      studentAttendance: $event.target.value

    }
    this.attendanceService.saveStudentAttendanceForSessionByTeacher(request).subscribe(r => this.session = r);
  }
  sendSession() {
    let req = {
      sessionAttendanceId: this.session.id
    }
    this.attendanceService.sendSession(req).subscribe(r => this.session = r)
  }

  saveSessionText(){
    let req = {
      sessionAttendanceId : this.session.id, sessionText : this.session.sessionText
    }
    this.attendanceService.saveSessionText(req).subscribe(r=>this.session=r)
  }

  getCountStudentByPresence(presenceCode) {
    return this.session.students.filter(s => s.presence == presenceCode).length;
  }

  getStudentsCount() {
    return this.session.students.length;
  }
}

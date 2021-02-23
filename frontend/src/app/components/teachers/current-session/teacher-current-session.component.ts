import { Component, OnInit } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { AttendanceService } from 'src/app/services/attendance.service';
import { UploadFileService } from 'src/app/services/upload-file.service';

@Component({
  selector: 'app-current-session',
  templateUrl: './teacher-current-session.component.html',
  styleUrls: ['./teacher-current-session.component.css']
})
export class TeacherCurrentSessionComponent implements OnInit {

  session: any;
  htmlContent: any;
  files;
  editorConfig: AngularEditorConfig = {
    editable: true,
    spellcheck: true,
    height: 'auto',
    minHeight: '430px',
  }
  constructor(public attendanceService: AttendanceService, private uploadFileService: UploadFileService) { }

  ngOnInit(): void {
    this.attendanceService.getCurrentSessionForTeacher().subscribe(r => {
      this.session = r;
      if(this.session)
      this.uploadFileService.getFilesBySessionId(this.session.id).subscribe(r => {
         this.files = r; 
        })
    })
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

  saveSessionText() {
    let req = {
      sessionAttendanceId: this.session.id, sessionText: this.session.sessionText
    }
    this.attendanceService.saveSessionText(req).subscribe(r => this.session = r)
  }

  getCountStudentByPresence(presenceCode) {
    return this.session.students.filter(s => s.presence == presenceCode).length;
  }

  getStudentsCount() {
    return this.session.students.length;
  }

  handleFileInput(files: FileList) {
    console.log(files.item(0));
  }

  downloadFile(file){
    console.log(file)
    this.uploadFileService.donwnloadFile(file.id, file.type).subscribe(r=> {
      console.log(r)
      let data :any = r;
      const blob = new Blob([data], { type: file.type });
      const url= window.URL.createObjectURL(blob);
      window.open(url);
    })
  }
}

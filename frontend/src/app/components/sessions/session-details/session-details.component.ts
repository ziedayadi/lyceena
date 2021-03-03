import { Component, Input, OnInit } from '@angular/core';
import { AttendanceService } from 'src/app/services/attendance.service';
import { UploadFileService } from 'src/app/services/upload-file.service';


@Component({
  selector: 'session-details',
  templateUrl: './session-details.component.html',
  styleUrls: ['./session-details.component.css']
})
export class SessionDetails implements OnInit {

  @Input()
  sessionId : Number; 

  @Input()
  readOnly : boolean = false; 

  session: any;
  htmlContent: any;
  files;
  constructor(public attendanceService: AttendanceService, private uploadFileService: UploadFileService) {}

  ngOnInit(): void {
    if(!this.sessionId){
      this.attendanceService.getCurrentSessionForTeacher().subscribe(r => {
        this.session = r;
        if(this.session)
          this.fetchFiles();
      })
    } else {
      this.attendanceService.getSessionById(this.sessionId).subscribe(r => {
        this.session = r;
        if(this.session)
          this.fetchFiles();
      })
    }
  }

  fetchFiles(){
    this.uploadFileService.getFilesBySessionId(this.session.id).subscribe(r => {
      this.files = r; 
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

  submitSession(){
    let req = {
      sessionAttendanceId: this.session.id
    }
    this.attendanceService.submitSession(req).subscribe(r => this.session = r) 
  }

  saveSessionText($event) {
  
    let req = {
      sessionAttendanceId: this.session.id, sessionText: $event
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
    this.uploadFileService.donwnloadFile(file.id, file.type).subscribe(r=> {
      console.log(r)
      let data :any = r;
      const blob = new Blob([data], { type: file.type });
      const url= window.URL.createObjectURL(blob);
      window.open(url);
    })
  }

  onUpload($event){
      this.fetchFiles();
  }

  deleteFile(f){
    this.uploadFileService.deleteFile(f.id).subscribe(()=>this.fetchFiles());
  }
}

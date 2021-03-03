import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AttendanceService } from 'src/app/services/attendance.service';
import { UploadFileService } from 'src/app/services/upload-file.service';

@Component({
  selector: 'app-student-session-details',
  templateUrl: './student-session-details.component.html',
  styleUrls: ['./student-session-details.component.css']
})
export class StudentSessionDetailsComponent implements OnInit {

  sessionDetails;
  files;

  constructor(private activatedRoute: ActivatedRoute,
    public attendanceService: AttendanceService,
    private uploadFileService: UploadFileService
  ) { }

  ngOnInit(): void {
    this.fetchSessionDetails();
  }
  private fetchSessionDetails() {
    const sessionId = this.activatedRoute.snapshot.paramMap.get('sessionId');
    this.attendanceService.getSessionForStudentBySessionId(sessionId).subscribe(r => {
      this.sessionDetails = r; 
      this.fetchFiles(); 
    })
  }

  getSessionPresence() {
    return {
      label: this.attendanceService.getStudentAttendanceTranslation(this.sessionDetails.students[0].presence),
      code: this.sessionDetails.students[0].presence
    }
  }

  fetchFiles() {
    this.uploadFileService.getFilesBySessionId(this.sessionDetails.id).subscribe(r => {
      this.files = r;
    })
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

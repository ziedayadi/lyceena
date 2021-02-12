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

  onChange($event,student){
    console.log(this.session)
    console.log(student)
    console.log($event.target.value)
    let request = {
      sessionAttendanceId : this.session.id,
      studentId : student.id, 
      studentAttendance : $event.target.value

    }

    console.log(request)
    this.attendanceService.saveStudentAttendanceForSessionByTeacher(request).subscribe(r=>this.session=r); 
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StudentsService } from 'src/app/services/students.service';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {
  student: any;

  constructor(private route : ActivatedRoute,
    private studentService : StudentsService) { }

  ngOnInit(): void {
    const studentId = this.route.snapshot.paramMap.get('studentId');
    this.studentService.findOneById(studentId).subscribe(r=> this.student = r)
  }

}

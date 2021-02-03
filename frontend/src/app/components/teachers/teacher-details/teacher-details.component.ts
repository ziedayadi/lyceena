import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TeachersService } from 'src/app/services/teachers.service';

@Component({
  selector: 'app-teacher-details',
  templateUrl: './teacher-details.component.html',
  styleUrls: ['./teacher-details.component.css']
})
export class TeacherDetailsComponent implements OnInit {

  teacher;
  classes; 
  title = "Enseignant"
  constructor(private route: ActivatedRoute,
    private teacherService : TeachersService) { }

  ngOnInit(): void {
    const teacherId = this.route.snapshot.paramMap.get('teacherId');
    this.teacherService.findOne(teacherId).subscribe(r=>this.teacher = r)
    this.teacherService.findClasses(teacherId).subscribe(r=>this.classes = r)
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { ClassesService } from 'src/app/services/classes.service';

@Component({
  selector: 'app-teachers-list',
  templateUrl: './teachers-list.component.html',
  styleUrls: ['./teachers-list.component.css']
})
export class TeachersListComponent implements OnInit {

  constructor(private classesService : ClassesService) { }

  @Input() classId;

  teachers;

  ngOnInit(): void {
    this.fetchTeachers()
  }

  fetchTeachers(){
    this.classesService.findTeachersByClassId(this.classId).subscribe(r=> this.teachers = r);
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { ClassesService } from 'src/app/services/classes.service';

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.css']
})
export class StudentsListComponent implements OnInit {

  @Input() classId; 
  students : []; 
  
  constructor(private classesService : ClassesService) { }


  ngOnInit(): void {
    this.fetchStudents();
  }

  private fetchStudents(){
    this.classesService.findStudentsByClassId(this.classId).subscribe(r=>this.students = r);
  }

  log(i){
  }

}

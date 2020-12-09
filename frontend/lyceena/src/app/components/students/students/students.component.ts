import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'sb-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.scss']
})
export class StudentsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    console.log('STUDENTS')
  }

}

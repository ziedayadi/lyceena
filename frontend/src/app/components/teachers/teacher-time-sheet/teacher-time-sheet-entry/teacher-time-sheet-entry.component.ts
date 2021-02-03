import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-teacher-time-sheet-entry',
  templateUrl: './teacher-time-sheet-entry.component.html',
  styleUrls: ['./teacher-time-sheet-entry.component.css']
})
export class TeacherTimeSheetEntryComponent implements OnInit {

  @Input() session; 

  constructor() { }

  ngOnInit(): void {
    console.log(this.session)
  }

}

import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-time-sheet-element',
  templateUrl: './time-sheet-element.component.html',
  styleUrls: ['./time-sheet-element.component.css']
})
export class TimeSheetElementComponent implements OnInit {

  constructor() { }

  @Input() session;

  ngOnInit(): void {
  }

}

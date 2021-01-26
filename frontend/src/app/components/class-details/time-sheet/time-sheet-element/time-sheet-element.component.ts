import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UpdateSessionDialogComponent } from '../update-session-dialog/update-session-dialog.component';

@Component({
  selector: 'app-time-sheet-element',
  templateUrl: './time-sheet-element.component.html',
  styleUrls: ['./time-sheet-element.component.css']
})
export class TimeSheetElementComponent implements OnInit {

  constructor(public dialog : MatDialog) { }


  @Input() session;

  dialogRef : any;
  ngOnInit(): void {
  }

  onUpdateSession(){
      this.dialogRef = this.dialog.open(UpdateSessionDialogComponent, {
        data : {
          session : this.session, width : 1200
        }
      })
  }
}

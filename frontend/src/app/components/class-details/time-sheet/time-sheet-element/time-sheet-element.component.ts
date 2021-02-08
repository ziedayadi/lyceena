import { Component, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EventEmitter } from '@angular/core';
import { UpdateSessionDialogComponent } from '../update-session-dialog/update-session-dialog.component';

@Component({
  selector: 'app-time-sheet-element',
  templateUrl: './time-sheet-element.component.html',
  styleUrls: ['./time-sheet-element.component.css']
})
export class TimeSheetElementComponent implements OnInit {

  constructor(public dialog : MatDialog) { }


  @Input() session;
  @Input() readOnly : boolean = false; 
  @Output() change = new EventEmitter();

  dialogRef : any;
  ngOnInit(): void {
  }

  onUpdateSession(){
      this.dialogRef = this.dialog.open(UpdateSessionDialogComponent, {
        data : {
          session : this.session, width : 1200
        }
      })
      this.dialogRef.componentInstance.onSaveAction.subscribe(r=> {
        this.change.emit(r)
      })
  }
}

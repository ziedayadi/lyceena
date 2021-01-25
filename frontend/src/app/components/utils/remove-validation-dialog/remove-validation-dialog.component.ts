import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-remove-validation-dialog',
  templateUrl: './remove-validation-dialog.component.html',
  styleUrls: ['./remove-validation-dialog.component.css']
})
export class RemoveValidationDialogComponent implements OnInit {

  public onYesAction = new EventEmitter();
  
  constructor(public dialogRef: MatDialogRef<RemoveValidationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit(): void {
  }

  onNo(){
    this.dialogRef.close(); 
  }

  onYes(): void{
    this.onYesAction.emit('DELETE OK');
  }

}

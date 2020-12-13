import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EventEmitter } from  '@angular/core';

@Component({
  selector: 'app-crud-table-dialog',
  templateUrl: './crud-table-dialog.component.html',
  styleUrls: ['./crud-table-dialog.component.css']
})
export class CrudTableDialogComponent implements OnInit {



  constructor(public dialogRef: MatDialogRef<CrudTableDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  public onSaveAction = new EventEmitter();

  ngOnInit(): void {
    console.log(this.data)
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSaveClick(): void {
    this.onSaveAction.emit(this.data.editedItem); 
  }
  getEditableFields() {
    return this.data.fields.filter(f => !f.hidden);
  }

}

import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EventEmitter } from  '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-crud-table-dialog',
  templateUrl: './crud-table-dialog.component.html',
  styleUrls: ['./crud-table-dialog.component.css']
})
export class CrudTableDialogComponent implements OnInit {

  validatingForm: FormGroup;
  formGroup : FormGroup;


  constructor(public dialogRef: MatDialogRef<CrudTableDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  public onSaveAction = new EventEmitter();

  ngOnInit(): void {
    this.validatingForm = new FormGroup({
      required: new FormControl(null, Validators.required)
    });

  
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
  get input() { return this.validatingForm.get('required'); }

}

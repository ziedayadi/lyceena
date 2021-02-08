import { AfterViewChecked, ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-crud-table-dialog',
  templateUrl: './crud-table-dialog.component.html',
  styleUrls: ['./crud-table-dialog.component.css']
})
export class CrudTableDialogComponent implements OnInit, AfterViewChecked {

  validatingForm: FormGroup;


  constructor(public dialogRef: MatDialogRef<CrudTableDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private cdRef:ChangeDetectorRef) {
  }

  ngAfterViewChecked(){
    // This method is to prevent Worning of after view changed
    this.cdRef.detectChanges();

  }

  public onSaveAction = new EventEmitter();

  ngOnInit(): void {
    console.log(this.data.editedItem)
    this.initFormControlGroup();
  }

  initFormControlGroup() {
    let group = {}
    this.data.fields.forEach(field => {
      // group[field.field] = new FormControl({ value: field.field, disabled: field.disabled }, [Validators.required]);
      group[field.field] = new FormControl({ value: field.field, disabled: field.disabled });
      if (field.required) {
        group[field.field].setValidators([Validators.required]);
      }
    })
    this.validatingForm = new FormGroup(group);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSaveClick(): void {
    if (this.validatingForm.status == 'VALID')
      this.onSaveAction.emit(this.data.editedItem);
  }
  getEditableFields() {
    return this.data.fields.filter(f => !f.hidden);
  }
}

import { Component, Input, OnInit, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';


@Component({
  selector: 'app-lyceena-editor',
  templateUrl: './lyceena-editor.component.html',
  styleUrls: ['./lyceena-editor.component.css']
})
export class LyceenaEditorComponent implements OnInit {

  @Input() disabled : boolean = false; 
  @Output() save = new EventEmitter();


  @Input()
  @Output()
  text : '';

  readOnly = false

  config = {
    extraPlugins: 'divarea',
    width : '100%',
    height : '380px',
    removePlugins : 'image,paste',
    language : 'fr',
    readOnly : this.disabled
  }


  constructor() { }

  ngOnInit(): void {
  }

  onSave(){
    this.save.emit(this.text);
  }

}

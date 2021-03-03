import { Component, Input, OnInit, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';


@Component({
  selector: 'app-lyceena-editor',
  templateUrl: './lyceena-editor.component.html',
  styleUrls: ['./lyceena-editor.component.css']
})
export class LyceenaEditorComponent implements OnInit {

  @Output() save = new EventEmitter();


  @Input()
  @Output()
  text : '';

  @Input()
  height = '380';

  @Input()
  readOnly : boolean = false;

  config = {
    extraPlugins: 'divarea',
    width : '100%',
    height : this.height,
    removePlugins : 'image,paste',
    language : 'fr',
    readOnly : false
  }


  constructor() { 
    
  }

  ngOnInit(): void {
    this.config.readOnly = this.readOnly; 
    this.config.height = this.height + 'px'; 
  }

  onSave(){
    this.save.emit(this.text);
  }

}

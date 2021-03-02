import { Component, ElementRef, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EventEmitter } from '@angular/core';
import { UploadFileService } from 'src/app/services/upload-file.service';
@Component({
  selector: 'app-file-uploader',
  templateUrl: './file-uploader.component.html',
  styleUrls: ['./file-uploader.component.css']
})
export class FileUploaderComponent implements OnInit {

  @Input()
  sessionId;

  @Input()
  disabled : boolean = false; 

  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  fileInfos;


  @ViewChild('labelImport')
  labelImport: ElementRef;

  @Output() 
  upload = new EventEmitter();

  formImport: FormGroup;
  fileToUpload: File = null;

  constructor(private uploadService: UploadFileService) {
    this.formImport = new FormGroup({
      importFile: new FormControl('', Validators.required)
    });
   }

  ngOnInit(): void {
    console.log(this.disabled)
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  onFileChange(files: FileList) {
    this.labelImport.nativeElement.innerText = Array.from(files)
      .map(f => f.name)
      .join(', ');
    this.selectedFiles = files;
  }

  import() { 
    this.progress = 0;
    this.currentFile = this.selectedFiles.item(0);
    this.uploadService.upload(this.currentFile, this.sessionId).subscribe(r => {
      this.upload.emit('upload-done')
      this.reset();
    })
  }

  reset() {
    this.selectedFiles = null; 
    this.labelImport.nativeElement.innerText = '';
  }

}

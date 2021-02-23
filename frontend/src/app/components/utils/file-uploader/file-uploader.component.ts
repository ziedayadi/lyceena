import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UploadFileService } from 'src/app/services/upload-file.service';
@Component({
  selector: 'app-file-uploader',
  templateUrl: './file-uploader.component.html',
  styleUrls: ['./file-uploader.component.css']
})
export class FileUploaderComponent implements OnInit {

  @Input()
  sessionId;

  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  fileInfos;

  @ViewChild('fileInput')
  inputFileElement: ElementRef;

  @ViewChild('labelImport')
  labelImport: ElementRef;

  formImport: FormGroup;
  fileToUpload: File = null;

  constructor(private uploadService: UploadFileService) {
    this.formImport = new FormGroup({
      importFile: new FormControl('', Validators.required)
    });
   }

  ngOnInit(): void {
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
      this.reset();
    })
  }

  reset() {
    this.selectedFiles = null; 
    this.labelImport.nativeElement.innerText = '';
  }

}

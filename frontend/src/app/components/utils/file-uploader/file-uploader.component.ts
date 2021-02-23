import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
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

  constructor(private uploadService: UploadFileService) { }

  ngOnInit(): void {
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.progress = 0;
    this.currentFile = this.selectedFiles.item(0);
    this.uploadService.upload(this.currentFile, this.sessionId).subscribe(r => {
      this.reset();
    })
  }

  reset() {
    this.selectedFiles = null; 
    this.inputFileElement.nativeElement.value = "";
  }

}

import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RefService } from 'src/app/services/ref.service';
import { ClassRoomsService } from 'src/app/services/class-rooms.service';

@Component({
  selector: 'app-update-session-dialog',
  templateUrl: './update-session-dialog.component.html',
  styleUrls: ['./update-session-dialog.component.css']
})
export class UpdateSessionDialogComponent implements OnInit {

  oldSession : any; 
  newSession : any;
  days : any[]; 
  hours : any[]; 
  classRooms : any[];


  constructor(public dialogRef: MatDialogRef<UpdateSessionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private refService : RefService,
    private classRoomsService : ClassRoomsService) {
  }

  ngOnInit(): void {
    this.oldSession = this.data.session;
    
    this.newSession =  {
      dayId : this.oldSession.dayOfWeek.id , startHourId : this.oldSession.startHour.id , endHourId : null, classRoomId : this.oldSession.classRoom.id
    }

    this.refService.findDays().subscribe(r=>  this.days = r)
    this.refService.findHours().subscribe(r=>  this.hours = r)
    this.classRoomsService.findAll().subscribe(r=>this.classRooms = r)
    console.log(this.data.session)
  }

  onOk(){
    console.log(this.newSession)
    this.dialogRef.close();
  }

  onCancel(){
    this.dialogRef.close(); 
  }


}

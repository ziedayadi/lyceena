import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RefService } from 'src/app/services/ref.service';
import { ClassRoomsService } from 'src/app/services/class-rooms.service';
import { ClassesService } from 'src/app/services/classes.service';

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
  freeHoursForClassAndTeacher : any;
  freeClassRooms : any; 


  constructor(public dialogRef: MatDialogRef<UpdateSessionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private refService : RefService,
    private classRoomsService : ClassRoomsService,
    private classService : ClassesService) {
  }

  ngOnInit(): void {
    this.oldSession = this.data.session;
    
    this.newSession =  {
      dayId : this.oldSession.dayOfWeek.id , startHourId : this.oldSession.startHour.id , endHourId : null, classRoomId : this.oldSession.classRoom.id
    }

    this.refService.findDays().subscribe(r=>  this.days = r)
    this.refService.findHours().subscribe(r=>  this.hours = r)
    this.classService.findFreeHoursByClassIdAndTeacherId(this.oldSession.clazz.id, this.oldSession.teacher.id)
    .subscribe(r=> this.freeHoursForClassAndTeacher = r)
    this.classRoomsService.findAll().subscribe(r=>this.classRooms = r)
  }

  onOk(){
    console.log(this.newSession)
    this.dialogRef.close();
  }

  onCancel(){
    this.dialogRef.close(); 
  }

  getFreeDays(){
    return this.freeHoursForClassAndTeacher ? this.freeHoursForClassAndTeacher
    .map(e=>e.day)
    .filter((value, index, self) => self.map(d=>d.id).indexOf(value.id) === index)
    .sort((a, b) => (a.id < b.id ? -1 : 1)): [];
  }

  getFreeHours(){
    return this.freeHoursForClassAndTeacher && this.newSession.dayId ? 
      this.freeHoursForClassAndTeacher.filter(e=> e.day.id == this.newSession.dayId).map(e=>e.hour) : []
  }

  onDayChange(d){
    this.newSession.startHourId = ''
    this.newSession.endHourId = ''
    this.newSession.classRoomId = ''
    this.fetchFreeClassRooms()
  }

  onStartHourChange(h){
    let startHourIndex = this.hours.map(e => e.id+'').indexOf(h); 
    this.newSession.endHourId = this.hours[startHourIndex+1].id
    this.newSession.classRoomId = ''
    this.fetchFreeClassRooms();

  }

  fetchFreeClassRooms(){
    this.freeClassRooms = []; 
    if(this.newSession.dayId, this.newSession.startHourId)
      this.classRoomsService.free(this.newSession.dayId, this.newSession.startHourId).subscribe(r=> this.freeClassRooms = r)
  }

}

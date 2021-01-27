import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RefService } from 'src/app/services/ref.service';
import { ClassRoomsService } from 'src/app/services/class-rooms.service';
import { ClassesService } from 'src/app/services/classes.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-update-session-dialog',
  templateUrl: './update-session-dialog.component.html',
  styleUrls: ['./update-session-dialog.component.css']
})
export class UpdateSessionDialogComponent implements OnInit {



  updateSessionForm ;  

  oldSession : any; 
  newSession : any;
  days : any[]; 
  hours : any[]; 
  classRooms : any[];
  freeHoursForClassAndTeacher : any;
  freeClassRooms : any; 
  public onSaveAction = new EventEmitter();


  constructor(public dialogRef: MatDialogRef<UpdateSessionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private refService : RefService,
    private classRoomsService : ClassRoomsService,
    private classService : ClassesService) {
  }

  ngOnInit(): void {
    this.oldSession = this.data.session;
    console.log(this.oldSession)
    
    this.newSession =  {
      oldSessionId : this.oldSession.id , dayId : null , startHourId : null , endHourId : null, classRoomId : null
    }

    this.refService.findDays().subscribe(r=>  this.days = r)
    this.refService.findHours().subscribe(r=>  this.hours = r)
    this.classService.findFreeHoursByClassIdAndTeacherId(this.oldSession.clazz.id, this.oldSession.teacher.id)
    .subscribe(r=> this.freeHoursForClassAndTeacher = r)
    this.classRoomsService.findAll().subscribe(r=>this.classRooms = r)

    this.updateSessionForm = new FormGroup({
      classRoom: new FormControl(this.newSession.classRoomId, [
        Validators.required,
      ])
    });
  }

  onOk(){
    console.log(this.newSession)
    this.classService.updateSession(this.newSession).subscribe(()=>{
      this.onSaveAction.emit(this.newSession)
      this.dialogRef.close();
    })
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

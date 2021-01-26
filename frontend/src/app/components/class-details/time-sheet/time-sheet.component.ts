import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ClassesService } from 'src/app/services/classes.service';
import { RefService } from 'src/app/services/ref.service';
import { ValidationDialogComponent } from '../../utils/remove-validation-dialog/validation-dialog.component';

const emptySession = {
  material: {
    name: ''
  }
}

@Component({
  selector: 'app-time-sheet',
  templateUrl: './time-sheet.component.html',
  styleUrls: ['./time-sheet.component.css']
})
export class TimeSheetComponent implements OnInit {


  dialogRef : any;
  @Input() classId;

  sessions: any;
  days: any;
  hours: any;

  constructor(private classesService: ClassesService, private refService: RefService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.fetchDays();
    this.fetchHours();
    this.fetchSessions();
  }
  private fetchHours() {
    this.refService.findHours().subscribe(r => this.hours = r);
  }

  private fetchDays() {
    this.refService.findDays().subscribe(r => this.days = r);
  }

  private fetchSessions() {
    this.classesService.findSessionssByClassId(this.classId).subscribe(r => this.sessions = r)
  }

  getSession(day, hour) {
    const session = this.sessions.filter(s => (s.dayOfWeek && s.startHour && s.dayOfWeek.id === day && s.startHour.id === hour.id));
    if (session.length > 0) {
      return session[0]
    }

  }

  onClickGenerateNewTimeSheet(){
    this.dialogRef = this.dialog.open(ValidationDialogComponent, {
      width: '500px',
      data: { 
        message :  'Êtes-vous sûr de vouloir Regénerer l\'emploi du temps?'
      }
    });

    this.dialogRef.componentInstance.onYesAction.subscribe(r=> {
      this.classesService.createTimeSheet(this.classId).subscribe(r=>this.sessions=r)
      this.dialogRef.close();
    })
  }

  getNonAssignedSessionsCount(){
    if(this.sessions)
    return this.sessions.filter(s => (s.dayOfWeek == null || s.startHour == null)).length;
    else return 0
  }



}

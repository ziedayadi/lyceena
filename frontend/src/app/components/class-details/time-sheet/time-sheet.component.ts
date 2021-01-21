import { Component, Input, OnInit } from '@angular/core';
import { ClassesService } from 'src/app/services/classes.service';
import { RefService } from 'src/app/services/ref.service';

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



  @Input() classId;

  sessions: any;
  days: any;
  hours: any;

  constructor(private classesService: ClassesService, private refService: RefService) { }

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
    const session = this.sessions.filter(s => (s.dayOfWeek.id === day && s.startHour.id === hour.id));
    if (session.length > 0) {
      return session[0]
    }

  }

  onClickGenerateNewTimeSheet(){
    
  }



}

import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { AttendanceService } from 'src/app/services/attendance.service';

@Component({
  selector: 'app-admin-sessions-list',
  templateUrl: './admin-sessions-list.component.html',
  styleUrls: ['./admin-sessions-list.component.css']
})
export class AdminSessionsListComponent implements OnInit {

  constructor(public attendanceService: AttendanceService) { }

  sessions;
  dataSource: MatTableDataSource<any>;
  displayedColumns: string[] = ['date','class', 'material' ,'teacher', 'hour', 'class_room' , 'status' , 'action'];
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.fetchSessions()
  }

  private fetchSessions() {
    this.attendanceService.getAdminSessions().subscribe(r => {
      this.sessions = r;
      this.dataSource = new MatTableDataSource(this.sessions);
      this.dataSource.paginator = this.paginator;
    });
  }

}

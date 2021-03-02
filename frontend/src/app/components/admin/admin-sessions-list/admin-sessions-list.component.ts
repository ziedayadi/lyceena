import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
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
  displayedColumns: string[] = ['date', 'class', 'material', 'teacher', 'hour', 'class_room', 'status', 'action'];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) matSort: MatSort;

  ngOnInit(): void {
    this.fetchSessions()
  }

  private fetchSessions() {
    this.attendanceService.getAdminSessions().subscribe(r => {
      this.sessions = r;
      this.dataSource = new MatTableDataSource(this.sessions);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.matSort;
      this.dataSource.sortingDataAccessor = this.sortingDataAccessor();
    });
  }

  sortingDataAccessor() {
    return (item, property) => {
      switch (property) {
        case 'class': return item.classMaterialSession.clazz.levelName + item.classMaterialSession.clazz.name
        case 'material': return item.classMaterialSession.material.name
        case 'teacher': return item.classMaterialSession.teacher.firstName + item.classMaterialSession.teacher.lastName
        case 'hour': return item.classMaterialSession.startHour.code
        case 'class_room': return item.classMaterialSession.classRoom.name
        case 'status': return this.attendanceService.getSessionAttendanceTraduction(item.status)
        default:
          return item[property];
      }
    };
  }

}

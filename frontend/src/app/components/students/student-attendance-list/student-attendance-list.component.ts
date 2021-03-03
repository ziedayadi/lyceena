import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AttendanceService } from 'src/app/services/attendance.service';

@Component({
  selector: 'app-student-attendance-list',
  templateUrl: './student-attendance-list.component.html',
  styleUrls: ['./student-attendance-list.component.css']
})
export class StudentAttendanceListComponent implements OnInit {

  attendances; 
  dataSource: MatTableDataSource<any>;
  displayedColumns: string[] = ['date','scheduling','material','teacher','classRoom','presence'];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) matSort: MatSort;
  
  constructor(public attendanceService : AttendanceService) { }

  ngOnInit(): void {
    this.fetchAttendance();
  }

  fetchAttendance(){
    this.attendanceService.getAttendanceForConnectedStudent().subscribe(r=>{
      this.attendances = r;
      this.dataSource = new MatTableDataSource(this.attendances); 
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.matSort;
      this.dataSource.sortingDataAccessor = this.sortingDataAccessor();
    })
  }


  sortingDataAccessor() {
    return (item, property) => {
      switch (property) {
        case 'date': return item.sessionAttendance.date
        case 'scheduling': return item.sessionAttendance.classMaterialSession.dayOfWeek.id + item.sessionAttendance.classMaterialSession.startHour.code
        case 'material': return item.sessionAttendance.classMaterialSession.material.name
        case 'teacher': return item.sessionAttendance.classMaterialSession.teacher.firstName + item.sessionAttendance.classMaterialSession.teacher.lastName
        case 'classRoom': return item.sessionAttendance.classMaterialSession.classRoom.name
        default:
          return item[property];
      }
    };
  }

}

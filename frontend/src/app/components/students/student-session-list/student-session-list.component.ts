import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AttendanceService } from 'src/app/services/attendance.service';
import { DateFormatPipe } from '../../utils/lyceena-date-pipes/date-format.pipe';

@Component({
  selector: 'app-student-session-list',
  templateUrl: './student-session-list.component.html',
  styleUrls: ['./student-session-list.component.css']
})
export class StudentSessionListComponent implements OnInit {

  sessions; 
  dataSource: MatTableDataSource<any>;
  displayedColumns: string[] = ['date', 'material', 'teacher', 'hour', 'class_room', 'action'];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) matSort: MatSort;


  constructor(public attendanceService : AttendanceService, public datepipe : DateFormatPipe) { }

  ngOnInit(): void {
    this.fetchSessions();
  }

  private createDatasource(){
    this.dataSource = new MatTableDataSource(this.sessions);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.matSort;
    this.dataSource.sortingDataAccessor = this.sortingDataAccessor();
    this.dataSource.filterPredicate = this.tableFilter();
  }

  private fetchSessions() {
    this.attendanceService.getStudentSessions().subscribe(r=>{
      this.sessions = r; 
      this.createDatasource();
    })
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

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  tableFilter() {
    return (data, filter: string) => {
      return (data.classMaterialSession.clazz.levelName.trim() +' '+ data.classMaterialSession.clazz.name.trim()).toLowerCase().indexOf(filter.trim().toLowerCase()) > -1 ||
        data.classMaterialSession.dayOfWeek.fr.toLowerCase().indexOf(filter.toLowerCase()) !== -1 ||
        (data.classMaterialSession.teacher.firstName + ' ' +  data.classMaterialSession.teacher.lastName).toLowerCase().indexOf(filter.toLowerCase()) !== -1 ||
        data.classMaterialSession.material.name.toLowerCase().indexOf(filter.toLowerCase()) !== -1 ||
        data.classMaterialSession.classRoom.name.toLowerCase().indexOf(filter.toLowerCase()) !== -1 ||
        this.attendanceService.getSessionAttendanceTraduction(data.status).toLowerCase().indexOf(filter.toLowerCase()) !== -1 ||
        this.datepipe.transform(data.date).toString().toLowerCase().indexOf(filter.toLowerCase()) !== -1
    }
  }

}

import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { AttendanceService } from 'src/app/services/attendance.service';
import {MatTableDataSource} from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-session-list',
  templateUrl: './session-list.component.html',
  styleUrls: ['./session-list.component.css']
})
export class SessionListComponent implements OnInit , AfterViewInit {

  sessions;
  dataSource: MatTableDataSource<any>;
  displayedColumns: string[] = ['date', 'class','hour', 'status' ];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) matSort: MatSort;

  constructor(private attendanceService : AttendanceService) { }

  ngOnInit(): void {
      this.fetchSessions();
  }

  ngAfterViewInit() {
 
  }

  private fetchSessions(){
    this.attendanceService.getTeacherSessions().subscribe(r=>{
      this.sessions = r
      this.dataSource = new MatTableDataSource(this.sessions);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sortingDataAccessor = (item, property) => {
        switch (property) {
            case 'class':
                return item.classMaterialSession.clazz.levelName + ' ' + item.classMaterialSession.clazz.name;
            default:
                return item[property];
        }
    };
      
      this.dataSource.sort = this.matSort;
    });
  }

  applyFilter(event: Event) {
    console.log(event)
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}

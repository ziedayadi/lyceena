import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClassLevelsRefDetailsComponent } from './components/class-levels-ref-details/class-levels-ref-details.component';
import { ClassDetailsComponent } from './components/class-details/class-details.component';
import { ClassLevelsRefComponent } from './components/class-levels-ref/class-levels-ref.component';
import { ClassesComponent } from './components/classes/classes.component';
import { EmployeesComponent } from './components/employees/employees.component';
import { MaterialsComponent } from './components/materials/materials.component';
import { ParentsComponent } from './components/parents/parents.component';
import { StudentsComponent } from './components/students/students.component';
import { TeachersComponent } from './components/teachers/teachers.component';
import { TeacherTimeSheetComponent } from './components/teachers/teacher-time-sheet/teacher-time-sheet.component';
import { AccountComponent } from './components/account/account.component';
import { ClassRoomsComponent } from './components/class-rooms/class-rooms.component';
import { ApplicationInfoComponent } from './components/application-info/application-info.component';
import { HttpTraceComponent } from './components/application-info/http-trace/http-trace.component';
import { ParentDetailsComponent } from './components/parents/parent-details/parent-details.component';
import { StudentDetailsComponent } from './components/students/student-details/student-details.component';
import { TeacherDetailsComponent } from './components/teachers/teacher-details/teacher-details.component';
import { SchoolContactComponent } from './components/school-contact/school-contact.component';
import { StudentTimesheetComponent } from './components/students/timesheet/timesheet.component';
import { SessionDetails } from './components/sessions/session-details/session-details.component';
import { SessionListComponent } from './components/teachers/session-list/session-list.component';
import { TeacherSerssionComponent } from './components/teachers/teacher-serssion/teacher-serssion.component';
import { AdminSessionsListComponent } from './components/admin/admin-sessions-list/admin-sessions-list.component';
import { AdminSessionDetailsComponent } from './components/admin/admin-session-details/admin-session-details.component';
import { StudentSessionListComponent } from './components/students/student-session-list/student-session-list.component';
import { StudentSessionDetailsComponent } from './components/students/student-session-details/student-session-details.component';

const routes: Routes = [
  {
    path : 'materials',
    component : MaterialsComponent
  },
  {
    path : 'students',
    component : StudentsComponent
  },
  {
    path : 'students/:studentId',
    component : StudentDetailsComponent
  },
  {
    path : 'student/timesheet',
    component : StudentTimesheetComponent
  },
  {
    path : 'student/sessions',
    component : StudentSessionListComponent
  },
  {
    path : 'student/session-list/session/:sessionId',
    component : StudentSessionDetailsComponent
  },
  {
    path : 'parents',
    component : ParentsComponent
  },
  {
    path : 'parents/:parentId',
    component : ParentDetailsComponent
  },
  {
    path : 'teachers',
    component : TeachersComponent
  },
  {
    path : 'teachers/:teacherId',
    component : TeacherDetailsComponent
  },
  {
    path : 'teacher/timesheet',
    component : TeacherTimeSheetComponent
  },
  {
    path : 'teacher/current-session',
    component : SessionDetails
  },
  {
    path : 'teacher/session-list',
    component : SessionListComponent
  }, //TeacherSerssionComponent
  {
    path : 'teacher/session-list/session/:sessionId',
    component : TeacherSerssionComponent
  },
  {
    path : 'class-levels-ref',
    component : ClassLevelsRefComponent
  },
  {
    path : 'class-levels-ref/:levelId',
    component : ClassLevelsRefDetailsComponent
  },
  {
    path : 'classes',
    component : ClassesComponent
  },
  {
    path : 'classes/:classId',
    component : ClassDetailsComponent
  },
  {
    path : 'employees',
    component : EmployeesComponent
  },
  {
    path : 'my-account',
    component : AccountComponent
  },
  {
    path : 'class-rooms',
    component : ClassRoomsComponent
  },
  {
    path : 'application-info',
    component : ApplicationInfoComponent
  },
  {
    path : 'http-trace',
    component : HttpTraceComponent
  },
  {
    path : 'school-contact',
    component : SchoolContactComponent
  }, 
  {
    path : 'admin/sessions',
    component : AdminSessionsListComponent
  },
  {
    path : 'admin/session-list/session/:sessionId',
    component : AdminSessionDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }

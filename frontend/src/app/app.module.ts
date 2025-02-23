import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MaterialsComponent } from './components/materials/materials.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { StudentsComponent } from './components/students/students.component';
import { TeachersComponent } from './components/teachers/teachers.component';
import { ParentsComponent } from './components/parents/parents.component';
import { ClassesComponent } from './components/classes/classes.component';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CrudTableComponent } from './components/utils/crud-table/crud-table.component';
import { DatePipe, HashLocationStrategy, LocationStrategy } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatDialogModule} from '@angular/material/dialog';
import { CrudTableDialogComponent } from './components/utils/crud-table-dialog/crud-table-dialog.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import {MatFormFieldModule} from '@angular/material/form-field';
import { ClassLevelsRefComponent } from './components/class-levels-ref/class-levels-ref.component';
import { EmployeesComponent } from './components/employees/employees.component';
import { ClassDetailsComponent } from './components/class-details/class-details.component';

import {MatSidenavModule} from '@angular/material/sidenav';
import { StudentsListComponent } from './components/class-details/students-list/students-list.component';
import { TimeSheetComponent } from './components/class-details/time-sheet/time-sheet.component';
import { TimeSheetElementComponent } from './components/class-details/time-sheet/time-sheet-element/time-sheet-element.component';
import { ClassLevelsRefDetailsComponent } from './components/class-levels-ref-details/class-levels-ref-details.component';
import { ClassDetailsMaterialsComponent } from './components/class-levels-ref-details/materials/materials.component';
import { TokenInterceptor } from './services/token-interceptor';
import { TeacherTimeSheetComponent } from './components/teachers/teacher-time-sheet/teacher-time-sheet.component';
import { AccountComponent } from './components/account/account.component';
import { ClassRoomsComponent } from './components/class-rooms/class-rooms.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTooltipModule} from '@angular/material/tooltip';
import { ValidationDialogComponent } from './components/utils/remove-validation-dialog/validation-dialog.component';
import { LoadingDialogComponent } from './components/utils/loading-dialog/loading-dialog.component';
import { UpdateSessionDialogComponent } from './components/class-details/time-sheet/update-session-dialog/update-session-dialog.component';
import { MaterialsListComponent } from './components/class-details/materials-list/materials-list.component';
import { ApplicationInfoComponent } from './components/application-info/application-info.component';
import { HttpTraceComponent } from './components/application-info/http-trace/http-trace.component';
import { ParentDetailsComponent } from './components/parents/parent-details/parent-details.component';
import { TeacherTimeSheetEntryComponent } from './components/teachers/teacher-time-sheet/teacher-time-sheet-entry/teacher-time-sheet-entry.component';
import { StudentDetailsComponent } from './components/students/student-details/student-details.component';
import { TeacherDetailsComponent } from './components/teachers/teacher-details/teacher-details.component';
import { SchoolContactComponent } from './components/school-contact/school-contact.component';
import { UserStatusComponent } from './components/utils/user-status/user-status.component';
import { DateFormatPipe } from './components/utils/lyceena-date-pipes/date-format.pipe';
import { DateTimeFormatPipe } from './components/utils/lyceena-date-pipes/date-time-format.pipe';
import { StudentTimesheetComponent } from './components/students/timesheet/timesheet.component';
import { SessionDetails } from './components/sessions/session-details/session-details.component';
import { FileUploaderComponent } from './components/utils/file-uploader/file-uploader.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { LyceenaEditorComponent } from './components/utils/lyceena-editor/lyceena-editor.component';
import { CKEditorModule } from 'ckeditor4-angular';
import { SessionListComponent } from './components/teachers/session-list/session-list.component';
import {MatPaginatorModule,MatPaginatorIntl} from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort';
import {MatInputModule} from '@angular/material/input';
import { TeacherSerssionComponent } from './components/teachers/teacher-serssion/teacher-serssion.component';
import { getfrenchPaginatorIntl } from './components/utils/fr-paginator-intl';
import { AdminSessionsListComponent } from './components/admin/admin-sessions-list/admin-sessions-list.component';
import { AdminSessionDetailsComponent } from './components/admin/admin-session-details/admin-session-details.component';
import { StudentSessionListComponent } from './components/students/student-session-list/student-session-list.component';
import { StudentSessionDetailsComponent } from './components/students/student-session-details/student-session-details.component';




@NgModule({
  declarations: [ 
    AppComponent,
    MaterialsComponent,
    NavbarComponent,
    StudentsComponent,
    TeachersComponent,
    ParentsComponent,
    ClassesComponent,
    CrudTableComponent,
    CrudTableDialogComponent,
    ClassLevelsRefComponent,
    EmployeesComponent,
    ClassDetailsComponent,
    StudentsListComponent,
    TimeSheetComponent,
    TimeSheetElementComponent,
    ClassLevelsRefDetailsComponent, 
    ClassDetailsMaterialsComponent, 
    TeacherTimeSheetComponent, 
    AccountComponent, 
    ClassRoomsComponent, 
    ValidationDialogComponent, 
    LoadingDialogComponent, 
    UpdateSessionDialogComponent, 
    MaterialsListComponent, 
    ApplicationInfoComponent, 
    HttpTraceComponent, 
    ParentDetailsComponent, 
    TeacherTimeSheetEntryComponent, 
    StudentDetailsComponent, 
    TeacherDetailsComponent, 
    SchoolContactComponent, 
    UserStatusComponent, 
    DateFormatPipe,
    DateTimeFormatPipe,
    StudentTimesheetComponent,
    SessionDetails,
    FileUploaderComponent,
    LyceenaEditorComponent,
    SessionListComponent,
    TeacherSerssionComponent,
    AdminSessionsListComponent,
    AdminSessionDetailsComponent,
    StudentSessionListComponent,
    StudentSessionDetailsComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatSidenavModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    MatTooltipModule,
    MDBBootstrapModule.forRoot(),
    MatExpansionModule,
    CKEditorModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule,
    MatInputModule
  ],
  providers: [
    DatePipe,DateFormatPipe,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    { provide: MatPaginatorIntl, useValue: getfrenchPaginatorIntl() }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

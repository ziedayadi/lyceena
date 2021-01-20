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
import { DatePipe } from '@angular/common';
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
import { TeachersListComponent } from './components/class-details/teachers-list/teachers-list.component';
import { TimeSheetComponent } from './components/class-details/time-sheet/time-sheet.component';
import { TimeSheetElementComponent } from './components/class-details/time-sheet/time-sheet-element/time-sheet-element.component';
import { ClassLevelsRefDetailsComponent } from './components/class-levels-ref-details/class-levels-ref-details.component';
import { ClassDetailsMaterialsComponent } from './components/class-levels-ref-details/materials/materials.component';
import { TokenInterceptor } from './services/token-interceptor';
import { TeacherTimeSheetComponent } from './components/teachers/teacher-time-sheet/teacher-time-sheet.component';
import { AccountComponent } from './components/account/account.component';
import { ClassRoomsComponent } from './components/class-rooms/class-rooms.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';




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
    TeachersListComponent,
    TimeSheetComponent,
    TimeSheetElementComponent,
    ClassLevelsRefDetailsComponent, 
    ClassDetailsMaterialsComponent, TeacherTimeSheetComponent, AccountComponent, ClassRoomsComponent

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
    MDBBootstrapModule.forRoot()

  ],
  providers: [
    DatePipe,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

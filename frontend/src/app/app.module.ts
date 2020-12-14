import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    MDBBootstrapModule.forRoot()

  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }

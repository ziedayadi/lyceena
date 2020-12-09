import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TeachersComponent } from './components/teachers/teachers/teachers.component';
import { MaterialsComponent } from './components/ref/materials/materials/materials.component'
import { StudentsComponent } from './components/students/students/students.component';
import { ClassesComponent } from './components/classes/classes.component'

@NgModule({
    declarations: [AppComponent, StudentsComponent, TeachersComponent, MaterialsComponent, ClassesComponent],
    imports: [BrowserModule, AppRoutingModule, HttpClientModule],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule {}

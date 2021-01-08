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
    path : 'parents',
    component : ParentsComponent
  },
  {
    path : 'teachers',
    component : TeachersComponent
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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

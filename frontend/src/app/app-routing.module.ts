import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClassLevelsRefComponent } from './components/class-levels-ref/class-levels-ref.component';
import { ClassesComponent } from './components/classes/classes.component';
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
    path : 'classes',
    component : ClassesComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

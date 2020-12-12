import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

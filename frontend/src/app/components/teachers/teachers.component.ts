import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { MaterialsService } from 'src/app/services/materials.service';
import { TeachersService } from 'src/app/services/teachers.service';
import { UserStatusService } from 'src/app/services/user-status.service';

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit {

  constructor(private teachersService: TeachersService,
    private userStatusService: UserStatusService,
    private materialsService: MaterialsService) { }
  crudSubject: Subject<void> = new Subject<void>();

  teachers: any[];

  title = 'Enseignants';
  heads = [
    {
      field: 'firstName',
      label: 'Prénom',
      type: 'text'
    },
    {
      field: 'lastName',
      label: 'Nom de famille',
      type: 'text'
    },
    {
      field: 'emailAdress',
      label: 'Email',
      type: 'text'
    },
    {
      field: 'phoneNumber',
      label: 'Numéro de téléphone',
      type: 'text'
    },
    {
      field: 'status',
      label: 'Statut',
      type: 'select',
      options: this.userStatusService.fetchAll()
    }
  ]
  newTeacher = {
    firstName: { value: '', text: '' },
    lastName: { value: '', text: '' },
    emailAdress: { value: '', text: '' },
    phoneNumber: { value: '', text: '' },
    status: { value: 'ACTIVE', text: '' },
    material: { value: '', text: '' },
  }

  ngOnInit(): void {
    this.fetchTeachers();
    this.fetMaterialsOptions();
  }

  private fetchTeachers() {
    this.teachersService.findAll().subscribe(r => {
      this.teachers = r.map(val => (
        {
          firstName: { value: val.firstName, text: val.firstName },
          lastName: { value: val.lastName, text: val.lastName },
          emailAdress: { value: val.emailAdress, text: val.emailAdress },
          phoneNumber: { value: val.phoneNumber, text: val.phoneNumber },
          status: { value: val.status, text: val.status },
          material: { value: val.material.id, text: val.material.name }
        }
      ))
    });
  }

  remove($event) {
    console.log($event)
  }
  save($event) {
    console.log($event)
  }

  fetMaterialsOptions() {
    this.materialsService.findAll().subscribe(r => {
      let ops = r.map(el => (
        {
          value: el.id,
          text: el.name
        }));

        console.log(ops)
      this.heads.push({
        field: 'material',
        label: 'Spécialité',
        type: 'select',
        options: ops
      });
    });


  }


}

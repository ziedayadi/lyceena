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
      field: 'userName',
      label: 'Code',
      type: 'text'
    },
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
      field: 'id',
      label: 'id',
      type: 'text',
      hidden : true
    },
    {
      field: 'status',
      label: 'Statut',
      type: 'select',
      options: this.userStatusService.fetchAll()
    }
  ]
  newTeacher = {
    userName: { value: '', text: '' },
    firstName: { value: '', text: '' },
    lastName: { value: '', text: '' },
    emailAdress: { value: '', text: '' },
    phoneNumber: { value: '', text: '' },
    status: { value: 'ACTIVE', text: '' },
    material: { value: '', text: '' },
    id: { value: '', text: '' },
  }

  ngOnInit(): void {
    this.fetchTeachers();
    this.fetMaterialsOptions();
  }

  private fetchTeachers() {
    this.teachersService.findAll().subscribe(r => {
      this.teachers = r.map(val => (
        {
          userName: { value: val.userName, text: val.userName },
          firstName: { value: val.firstName, text: val.firstName },
          lastName: { value: val.lastName, text: val.lastName },
          emailAdress: { value: val.emailAdress, text: val.emailAdress },
          phoneNumber: { value: val.phoneNumber, text: val.phoneNumber },
          status: { value: val.status, text: val.status },
          material: { value: val.material.id, text: val.material.name },
          id: { value: val.id, text: val.id },

        }
      ))
    });
  }

  remove($event) {
    this.teachersService.remove($event.id.value).subscribe(() => this.fetchTeachers())
  }
  save($event) {
    let dto = {
      userName: $event.userName.value,
      firstName: $event.firstName.value,
      lastName: $event.lastName.value,
      id: $event.id.value,
      status: $event.status.value,
      emailAdress: $event.emailAdress.value,
      phoneNumber : $event.phoneNumber.value,
      materialId : $event.material.value
    }
    this.teachersService.save(dto).subscribe(() => {
      console.log(dto)
      this.saveOK();
      this.fetchTeachers()
    })
  }

  saveOK() {
    this.crudSubject.next();
  }

  fetMaterialsOptions() {
    this.materialsService.findAll().subscribe(r => {
      let ops = r.map(el => (
        {
          value: el.id,
          text: el.name
        }));

      this.heads.push({
        field: 'material',
        label: 'Spécialité',
        type: 'select',
        options: ops
      });
    });


  }


}

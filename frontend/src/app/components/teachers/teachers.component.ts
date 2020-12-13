import { Component, OnInit } from '@angular/core';
import { TeachersService } from 'src/app/services/teachers.service';

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit {

  constructor(private teachersService: TeachersService) { }

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
      type: 'text'
    },
    {
      field: 'material',
      label: 'Spécialité',
      type: 'text'
    },
  ]

  ngOnInit(): void {
    this.fetchTeachers();
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
          material: {value : val.material.id, text : val.material.name}
        }
      ))
    });
  }



}

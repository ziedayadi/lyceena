import { Component, OnInit } from '@angular/core';
import { TeachersService } from 'src/app/services/teachers.service';

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit {

  constructor(private teachersService : TeachersService) { }

  teachers : any[]; 

  title = 'élèves'; 
  heads = [
    {
      field : 'firstName',
      label : 'Prénom'
    },
    {
      field : 'lastName',
      label : 'Nom de famille'
    },
    {
      field : 'emailAdress',
      label : 'Email'
    },
    {
      field : 'phoneNumber',
      label : 'Numéro de téléphone'
    },
    {
      field : 'status',
      label : 'Statut'
    },
    {
      field : 'material',
      label : 'Matière'
    },
  ]

  ngOnInit(): void {
    this.fetchTeachers(); 
  }

  private fetchTeachers(){
    this.teachersService.findAll().subscribe(r=>{
      this.teachers = r.map(val =>(
        {
          firstName : val.firstName,
          lastName : val.lastName,
          emailAdress : val.emailAdress,
          phoneNumber : val.phoneNumber,
          status : val.status,
          material :  val.material.name
        }
      ))
    }); 
  } 



}

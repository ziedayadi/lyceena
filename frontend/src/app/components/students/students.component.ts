import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { StudentsService } from 'src/app/services/students.service';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {

  constructor(private studentsService : StudentsService, public datepipe: DatePipe) { }

  students : any[]; 
  title = 'élèves'; 
  heads = [
    {
      field : 'id',
      label : 'id',
      hidden : true
    },
    {
      field : 'registrationNumber',
      label : 'Immatriculation'
    },
    {
      field : 'firstName',
      label : 'Prénom'
    },
    {
      field : 'lastName',
      label : 'Nom de famille'
    },
    {
      field : 'sex',
      label : 'Sexe'
    },
    {
      field : 'emailAdress',
      label : 'Email'
    },
    {
      field : 'status',
      label : 'Statut'
    },

    {
      field : 'class',
      label : 'Classe'
    },
    {
      field : 'birthDate',
      label : 'Date de naissance'
    },
    {
      field : 'parent',
      label : 'Parent'
    },

  ]

  ngOnInit(): void {
    this.fetchStudents()
  }

  fetchStudents(){
    this.studentsService.findAll().subscribe(r=> {
      this.students = r.map(val =>(
        {
          firstName : val.firstName,
          lastName : val.lastName,
          emailAdress : val.emailAdress,
          status : val.status,
          class :  val.aclass.level.name + ' - ' + val.aclass.name,
          birthDate : this.datepipe.transform(val.birthDate, 'dd-MM-yyyy') ,
          parent : val.parent.firstName + ' ' + val.parent.firstName.toUpperCase(),
          registrationNumber : val.registrationNumber,
          sex : val.sex,
          id : val.id
        }
      ))
    })
  }
  remove($event){
   this.studentsService.remove($event.id).subscribe(()=>{
     this.fetchStudents()
   })
   
  }

}

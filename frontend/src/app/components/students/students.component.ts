import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { ClassesService } from 'src/app/services/classes.service';
import { ParentsService } from 'src/app/services/parents.service';
import { StudentsService } from 'src/app/services/students.service';
import { UserStatusService } from 'src/app/services/user-status.service';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {

  constructor(private studentsService: StudentsService,
    private classesService: ClassesService,
    private parentsService: ParentsService,
    private userStatusService : UserStatusService,
    private router: Router,
    public datepipe: DatePipe) { }

  crudSubject: Subject<void> = new Subject<void>();

  saveOK() {
    this.crudSubject.next();
  }

  students: any[];
  title = 'élèves';
  classesOptions: any = [];
  parentsOptions: any = [];
  newStudent = {
    firstName: {
      text: '',
      value: ''
    },
    lastName: {
      value: '',
      text: ''
    },
    emailAdress: {
      value: '',
      text: ''
    },
    status: {
      value: 'ACTIVE',
      text: ''
    },
    class: {
      value: '',
      text: ''
    },
    birthDate: {
      text: '',
      value: '',
    },
    parent: {
      text: '',
      value: ''
    },
    registrationNumber: {
      value: '',
      text: ''
    },
    sex: {
      value: '',
      text: ''
    },
    id: {
      value: '',
      text: ''
    },

  };

  heads = [
    {
      field: 'registrationNumber',
      label: 'Immatricule',
      type: 'text',
      disabled: true,
      nav : null 
    },
    {
      field: 'firstName',
      label: 'Prénom',
      type: 'text',
      required : true,
      nav : null 

    },
    {
      field: 'lastName',
      label: 'Nom de famille',
      type: 'text',
      required : true,
            nav : null 

    },
    {
      field: 'sex',
      label: 'Sexe',
      type: 'select',
      options: [
        {
          value: 'M',
          text: 'Male'
        },
        {
          value: 'F',
          text: 'Femele'
        },
      ],
      required : true,
    },
    {
      field: 'emailAdress',
      label: 'Email',
      type: 'text',
      required : true,
    },
    {
      field: 'status',
      label: 'Statut',
      type: 'select',
      options: this.userStatusService.fetchAll(),
      required : true,

    },
    {
      field: 'birthDate',
      label: 'Date de naissance',
      type: 'date',
      required : true,
    },
    {
      field: 'id',
      label: 'id',
      hidden: true,
      type: 'text',
      required : true
    },

  ]

  ngOnInit(): void {
    this.fetchStudents();
    this.getClassesOptions();
    this.getParentOptions();
  }

  fetchStudents() {
    this.studentsService.findAll().subscribe(r => {
      this.students = r.map(val => (
        {
          firstName: {
            text: val.firstName,
            value: val.firstName
          },
          lastName: {
            value: val.lastName,
            text: val.lastName.toUpperCase()
          },
          emailAdress: {
            value: val.emailAdress,
            text: val.emailAdress
          },
          status: {
            value: val.status,
            text: val.status
          },
          class: {
            value: val.aclass.id,
            text: val.aclass.level.name + ' - ' + val.aclass.name
          },
          birthDate: {
            text: this.datepipe.transform(val.birthDate, 'dd-MM-yyyy'),
            value: this.datepipe.transform(val.birthDate, 'yyyy-MM-dd'),
          },
          parent: {
            text: val.parent.firstName + ' ' + val.parent.lastName.toUpperCase(),
            value: val.parent.id
          },
          registrationNumber: {
            value: val.registrationNumber,
            text: val.registrationNumber
          },
          sex: {
            value: val.sex,
            text: val.sex
          },
          id: {
            value: val.id,
            text: val.id
          },

        }
      ))
    })
  }
  remove($event) {
    this.studentsService.remove($event.id.value).subscribe(() => {
      this.fetchStudents();
      this.crudSubject.next();
    })
  }

  save($event) {
    let dto = {
      firstName: $event.firstName.value,
      lastName: $event.lastName.value,
      id: $event.id.value,
      parentId: $event.parent.value,
      registrationNumber: $event.registrationNumber.value,
      sex: $event.sex.value,
      status: $event.status.value,
      birthDate: $event.birthDate.value,
      classId: $event.class.value,
      emailAdress: $event.emailAdress.value,
    }

    this.studentsService.save(dto).subscribe(r => {
      this.saveOK();
      this.fetchStudents()
    })

  }

  getClassesOptions() {
    this.classesService.findAll().subscribe(r => {
      let options = r.map(el => (
        {
          value: el.id,
          text: el.level.name + ' - ' + el.name,
        }));
      this.heads.push({
        field: 'class',
        label: 'Classe',
        type: 'select',
        options: options,
        required : true,
        nav : '/classes'
      });
    })
  }

  getParentOptions() {
    this.parentsService.findAll().subscribe(r => {
      let options = r.map(el => (
        {
          value: el.id,
          text: el.firstName + ' ' + el.lastName.toUpperCase(),
        }));
      this.heads.push({
        field: 'parent',
        label: 'parent',
        type: 'select',
        options: options,
        required : true,
        nav : '/parents'
      });
    })
  }

  onItemClick($event){
    this.router.navigateByUrl('/students/'+$event.id.value);
  }

}

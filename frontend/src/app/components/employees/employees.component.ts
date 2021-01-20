import { visitValue } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { EmployeesTypeRefService } from 'src/app/employees-type-ref.service';
import { EmployeesService } from 'src/app/services/employees.service';
import { UserStatusService } from 'src/app/services/user-status.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  constructor(private userStatusService: UserStatusService,
    private employeesService: EmployeesService,
    private employeesTypeRefService : EmployeesTypeRefService) { }

  crudSubject: Subject<void> = new Subject<void>();


  title = 'Employés';
  employees: [];
  newItem = {
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
    id: {
      value: '',
      text: ''
    },
    type: {
      value: '',
      text: ''
    }

  };

  heads = [
    {
      field: 'firstName',
      label: 'Prénom',
      type: 'text',
      required: true,
    },
    {
      field: 'lastName',
      label: 'Nom de famille',
      type: 'text',
      required: true,
    },
    {
      field: 'emailAdress',
      label: 'Email',
      type: 'text',
      required: true,
    },
    {
      field: 'status',
      label: 'Statut',
      type: 'select',
      options: this.userStatusService.fetchAll(),
      required: true,

    },
    {
      field: 'id',
      label: 'id',
      hidden: true,
      type: 'text',
      required: true
    },
  ];

  ngOnInit(): void {
    this.fetchEmployeeTypes();
    this.fetchEmployees();
  }

  private fetchEmployeeTypes(){
    this.employeesTypeRefService.findAll().subscribe(r=>{
      let empTypesOptions = r.map(val => ({
        value : val.id, 
        text : val.name
      })); 
      this.heads.push( {
        field: 'type',
        label: 'Type',
        type: 'select',
        options: empTypesOptions,
        required: true,
      },)
    })
  }
  private fetchEmployees() {
    this.employeesService.findAll().subscribe(r=>{
      this.employees = r.map(val => (
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
          type: {
            text: val.type.name,
            value: val.type.id
          },
          id: {
            value: val.id,
            text: val.id
          },
        }
      ))
    })
  }


  save($event) {
    let dto = {
      firstName: $event.firstName.value,
      lastName: $event.lastName.value,
      id: $event.id.value,
      status: $event.status.value,
      emailAdress: $event.emailAdress.value,
      typeId : $event.type.value,
    }

    this.employeesService.save(dto).subscribe(r => {
      this.saveOK();
      this.fetchEmployees()
    }) 
  }

  remove($event) {
    let empId = $event.id.value;
    this.employeesService.remove(empId).subscribe(r=>this.fetchEmployees())
  }

  saveOK() {
    this.crudSubject.next();
  }

}

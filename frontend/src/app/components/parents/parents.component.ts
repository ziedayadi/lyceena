import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { ParentsService } from 'src/app/services/parents.service';
import { UserStatusService } from 'src/app/services/user-status.service';

@Component({
  selector: 'app-parents',
  templateUrl: './parents.component.html',
  styleUrls: ['./parents.component.css']
})
export class ParentsComponent implements OnInit {

  constructor(private parentsService : ParentsService,private userStatusService : UserStatusService) { }
  parents : any[]; 
  itemsPerPage = 10;
  title = 'Parents';
  crudSubject: Subject<void> = new Subject<void>();
  heads = [
    {
      field : 'firstName',
      label : 'Prénom',
      type: 'text'
    },
    {
      field : 'lastName',
      label : 'Nom de famille',
      type: 'text'
    },
    {
      field : 'emailAdress',
      label : 'Email',
      type: 'text'
    },
    {
      field : 'phoneNumber',
      label : 'Numéro de téléphone',
      type: 'text'
    },
    {
      field: 'status',
      label: 'Statut',
      type: 'select',
      options: this.userStatusService.fetchAll()
    },
  ]

  newParent = {
    firstName: {
      text: '',
      value:  '',
    },
    lastName: {
      value: '',
      text: '',
    },
    emailAdress: {
      value:  '',
      text:  '',
    },
    phoneNumber: {
      value:  '',
      text:  '',
    },
    id: {
      value: '',
      text:  '',
    },
    status: {
      value:  '',
      text:  '',
    },

  };

  ngOnInit(): void {
    this.fetchParents();
  }

  fetchParents(){
    this.parentsService.findAll().subscribe(r=> {
      this.parents = r.map(val => ({
        firstName: {
          text: val.firstName,
          value: val.firstName
        },
        lastName: {
          value: val.lastName,
          text: val.lastName
        },
        emailAdress: {
          value: val.emailAdress,
          text: val.emailAdress
        },
        phoneNumber: {
          value: val.phoneNumber,
          text: val.phoneNumber
        },
        id: {
          value: val.id,
          text: val.id
        },
        status: {
          value: val.status,
          text: val.status
        },
      }))
    })
  }

}

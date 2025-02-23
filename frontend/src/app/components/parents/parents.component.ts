import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';

import { ParentsService } from 'src/app/services/parents.service';
import { UserStatusService } from 'src/app/services/user-status.service';

@Component({
  selector: 'app-parents',
  templateUrl: './parents.component.html',
  styleUrls: ['./parents.component.css']
})
export class ParentsComponent implements OnInit {

  constructor(private parentsService : ParentsService,
    private userStatusService : UserStatusService,
    private router: Router) { }
  parents : any[]; 
  itemsPerPage = 10;
  title = 'parents';
  crudSubject: Subject<void> = new Subject<void>();
  heads = [
    {
      field: 'userName',
      label: 'Nom d\'utilisateur',
      type: 'text',
      required : true,
      nav : null 
    },
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
    {
      field: 'id',
      label: 'id',
      hidden: true,
      type: 'text',
      required : true
    },
  ]

  newParent = {
    userName: {
      text: '',
      value: ''
    },
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
        userName: {
          text: val.userName,
          value: val.userName
        },
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

  remove($event){
    this.parentsService.remove($event.id.value).subscribe(() => {
      this.crudSubject.next();
      this.fetchParents()
    },()=>this.crudSubject.next())
  }
  save($event) {
    let dto = {
      firstName: $event.firstName.value,
      lastName: $event.lastName.value,
      id: $event.id.value,
      status: $event.status.value,
      emailAdress: $event.emailAdress.value,
      phoneNumber : $event.phoneNumber.value,
      userName : $event.userName.value
    }

    this.parentsService.save(dto).subscribe(r => {
      this.saveOK();
      this.fetchParents()
    }) 
  }

  saveOK() {
    this.crudSubject.next();
  }

  onItemClick($event) {
    this.router.navigateByUrl('/parents/'+$event.id.value);
  }
}

import { Component, OnInit } from '@angular/core';
import { ParentsService } from 'src/app/services/parents.service';

@Component({
  selector: 'app-parents',
  templateUrl: './parents.component.html',
  styleUrls: ['./parents.component.css']
})
export class ParentsComponent implements OnInit {

  constructor(private parentsService : ParentsService) { }
  parents : any[]; 
  itemsPerPage = 10;
  title = 'Parents';
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
  ]

  ngOnInit(): void {
    this.fetchParents();
  }

  fetchParents(){
    this.parentsService.findAll().subscribe(r=>this.parents = r)
  }

}

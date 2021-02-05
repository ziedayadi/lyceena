import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-status',
  templateUrl: './user-status.component.html',
  styleUrls: ['./user-status.component.css']
})
export class UserStatusComponent implements OnInit {

  @Input() status; 
  constructor() { }

  ngOnInit(): void {
  }
  getToolTip(){
    if(this.status == 'ACTIVE') return 'Actif'
    if(this.status == 'DELETED') return 'Supprimé'
    else return 'Inactif'
  }

}

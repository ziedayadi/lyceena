import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserStatusService {

  constructor() { }

  fetchAll() {
    return [
      {
        value: 'DISABLED',
        text: 'Inactif'
      },
      {
        value: 'ACTIVE',
        text: 'Actif'
      },
      {
        value: 'DELETED',
        text: 'Supprimé'
      },
    ];
  }
}

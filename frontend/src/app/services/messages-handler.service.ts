import { HttpErrorResponse } from '@angular/common/http';
import { Injectable, NgZone } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subject, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageHandlerService {

  public subj_notification: Subject<string> = new Subject(); 
  constructor(private snackBar: MatSnackBar,
    private zone: NgZone) { }

  public handleError (error: HttpErrorResponse | any) {
    this.zone.run(() => {
      this.snackBar.open('HTTP-'+error.status + this.getMessage(error.status), 'fermer', {
        duration : 0,
        panelClass:  ['style-error']
      });
    });
    return throwError(error);
  }

  private getMessage(httpStatus : String){
    if(httpStatus == '403'){
      return ': Vous n\'avez pas l\'autorisation pour cette action'
    } else if(httpStatus == '401'){
      return ': Vous n\'êtes pas authentifié'
    } else if(httpStatus == '500'){
      return ': Une erreur coté serveur s\'est produite, contactez l\'administrateur système'
    } else return ''
  }

  public handleSuccess () {
    this.zone.run(() => {
      this.snackBar.open('Opération effectué avec succès','' , {
        duration : 500,
        panelClass:  ['style-success']
      });
    });
  }
}

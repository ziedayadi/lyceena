import { HttpErrorResponse } from '@angular/common/http';
import { Injectable, NgZone } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subject, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {

  public subj_notification: Subject<string> = new Subject(); 
  constructor(private snackBar: MatSnackBar,
    private zone: NgZone) { }

  public handleError (error: HttpErrorResponse | any) {
    this.open(error)
    return throwError(error);
  }

  public open(error: HttpErrorResponse): void {
    this.zone.run(() => {
      this.snackBar.open('HTTP-'+error.status, 'fermer', {
        duration : 0,
        panelClass: ['style-error']
      });
    });
  }
}

import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Cookie } from "ng2-cookies";
import { Observable, throwError  } from "rxjs";
import { map, catchError } from 'rxjs/operators';
import { ErrorHandlerService } from "./error-handler.service";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private errorHandlerService : ErrorHandlerService){}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const access_token = Cookie.get('access_token'); 
    const modifiedReq = req.clone({ 
      headers: req.headers.set('Authorization', `Bearer ${access_token}`),
    });
    return next.handle(modifiedReq).pipe(
      map((event: HttpEvent<any>) => {
        return event;
    }), catchError((error: HttpErrorResponse) => {
        this.errorHandlerService.open(error)
        return throwError(error);
 
     } )
    )
  }

  
  
}
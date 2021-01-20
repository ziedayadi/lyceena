import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Cookie } from "ng2-cookies";
import { Observable, throwError  } from "rxjs";
import { map, catchError } from 'rxjs/operators';
import { MessageHandlerService } from "./messages-handler.service";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private messageHandlerService : MessageHandlerService){}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const access_token = Cookie.get('access_token'); 
    const modifiedReq = req.clone({ 
      headers: req.headers.set('Authorization', `Bearer ${access_token}`),
    });
    return next.handle(modifiedReq).pipe(
      map((event:any) => {
        if(event.status == '200' && (modifiedReq.method == 'POST' || modifiedReq.method == 'DELETE' )){
          this.messageHandlerService.handleSuccess()
        }
        return event;
    }), catchError((error: HttpErrorResponse) => {
        this.messageHandlerService.handleError(error)
        return throwError(error);
     } )
    )
  }

  
  
}
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Cookie } from "ng2-cookies";
import { Observable, throwError  } from "rxjs";
import { map, catchError , finalize} from 'rxjs/operators';
import { LoaderService } from "./loader.service";
import { MessageHandlerService } from "./messages-handler.service";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private messageHandlerService : MessageHandlerService, private loaderService: LoaderService){}
  private count = 0;

  intercept(req: HttpRequest<any>, next: HttpHandler,): Observable<HttpEvent<any>> {
    
    const access_token = Cookie.get('access_token'); 
    const modifiedReq = req.clone({ 
      headers: req.headers.set('Authorization', `Bearer ${access_token}`),
    });
    if (this.count === 0) {
      this.loaderService.setHttpProgressStatus(true);
    }
    this.count++;
    return next.handle(modifiedReq).pipe(
      finalize(() => {
        this.count--;
        if (this.count === 0) {
          this.loaderService.setHttpProgressStatus(false);
        }
      }),
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
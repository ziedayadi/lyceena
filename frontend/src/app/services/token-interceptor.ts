import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Cookie } from "ng2-cookies";
import { Observable } from "rxjs";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const access_token = Cookie.get('access_token'); 
    const modifiedReq = req.clone({ 
      headers: req.headers.set('Authorization', `Bearer ${access_token}`),
    });
    return next.handle(modifiedReq);
  }
}
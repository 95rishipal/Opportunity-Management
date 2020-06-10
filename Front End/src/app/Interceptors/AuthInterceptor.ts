import { Injectable } from '@angular/core';
import { HttpEvent,HttpResponse,HttpErrorResponse, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Router} from '@angular/router';
@Injectable()
export class AuthInterceptor implements HttpInterceptor{
    constructor(private router: Router) {}
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{         
        let header = req.headers.append('Token', localStorage.getItem("Token"))
        header = header.append('Email',localStorage.getItem('Email'));
        header = header.append('Gid',localStorage.getItem('Gid'));
        const modifiedReq = req.clone({ 
                                headers: header,
                            });                    
         return next.handle(modifiedReq).pipe( tap(() => {},
         (err: any) => {
         if (err instanceof HttpErrorResponse) {
            
           if (err.status == 401) {
            this.router.navigate(['login']);
            alert("Session Expired!!\nLogin Again.");
           }
           return;
         }
       }));
    }

}
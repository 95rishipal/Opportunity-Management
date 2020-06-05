import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LocalstorageService } from '../Services/localstorage.service.service'
@Injectable()
export class AuthInterceptor implements HttpInterceptor{
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
        const userToken = 'secure-user-token';
        let header = req.headers.append('Token', localStorage.getItem("Token"))
        header = header.append('Email',localStorage.getItem('Email'));
        const modifiedReq = req.clone({ 
                                headers: header,
                            });
        // console.log(modifiedReq.headers);                    
         return next.handle(modifiedReq); 
    }

}
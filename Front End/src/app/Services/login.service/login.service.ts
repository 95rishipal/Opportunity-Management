import { Injectable } from '@angular/core';
import { HttpClient,  HttpHeaders } from "@angular/common/http";
import { Router } from '@angular/router'
import { environment } from '../../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiServer = environment.baseUrl;
  constructor(private httpClient: HttpClient, private Router:Router) { }

  public login(data){
    console.log("Request Header");
    console.log(data);
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',
    }); 
    let options = {
          headers: httpHeaders
    };
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    const body = data 
    return this.httpClient.post(this.apiServer+'/user/login', body, options);
  }

}

import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import {User} from '../../Models/User.model'
import { LocalstorageService } from '../localstorage.service.service'
import { Router } from '@angular/router'
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiServer = "http://localhost:8080";
  constructor(private httpClient: HttpClient, private Router:Router ) { }

  public login(data){
    console.log("Request Header");
    console.log(data);
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',
    }); 
    let options = {
          headers: httpHeaders
    };
    console.log(httpHeaders)
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    const body = data 
    return this.httpClient.post(this.apiServer+'/user/login', body, options);
  }

  public check():any{
    let httpHeaders = new HttpHeaders({
      
    }); 
    let options = {
          headers: httpHeaders
    };
    this.httpClient.post(this.apiServer+'/user/check', null ,options).subscribe((data)=>{
      return true;
    },error=>{
      alert("[Login] Session Expired");
      this.Router.navigate(["login"]);
    });
  }
}

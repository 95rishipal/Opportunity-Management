import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import {User} from '../../Models/User.model'
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiServer = "http://localhost:8080";
  constructor(private httpClient: HttpClient) { }

  public check(data){
    console.log(data)
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Cache-Control': 'no-cache'
    }); 
    let options = {
          headers: httpHeaders
    };
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    const body = data 
    return this.httpClient.post(this.apiServer+'/OppoM/api/login/gcheck', body, options);
  }

  public insert(data){
    console.log(data)
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Cache-Control': 'no-cache'
    }); 
    let options = {
          headers: httpHeaders,
    };
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    const body = data 
    return this.httpClient.post(this.apiServer+'/OppoM/api/login/glog', body, options);
  }

}

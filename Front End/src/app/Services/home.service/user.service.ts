import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServer = "http://localhost:8080";
  constructor(private httpClient: HttpClient) { }

  public getAllUser(){
      return this.httpClient.get(this.apiServer+'/user/getall');
  }

  public getCurrentUser(){
    return this.httpClient.get(this.apiServer+'/user/getCurrentUser');
}
}

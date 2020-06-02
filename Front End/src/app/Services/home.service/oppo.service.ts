import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})

export class OppoService {
  private apiServer = "http://localhost:8080";
  constructor(private httpClient: HttpClient) { }

  public getAllOpp(){
      return this.httpClient.get(this.apiServer+'/oppo/getall');
  }

  public del(id){
    return this.httpClient.delete(this.apiServer+'/oppo/del/'+id);
  }

  public addOpp(Opportunity){
    console.log(Opportunity)
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',
    }); 

    let options = {
          headers: httpHeaders
    };
    const body = Opportunity;
    return this.httpClient.post(this.apiServer+'/oppo/add', body, options);
  }

  public editOpp(Opportunity){
    console.log(Opportunity)
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Cache-Control': 'no-cache'
    }); 
    let options = {
          headers: httpHeaders
    }; 
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    const body = Opportunity
    return this.httpClient.post(this.apiServer+'/oppo/edit/'+Opportunity.oppid, body, options);

  }

}

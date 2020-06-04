import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})

export class  AuditService{
  private apiServer = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

  public demo(){
    console.log("Demo");
  }
  public search(col:String, place:String){
    return this.httpClient.get(this.apiServer+'/audit/search/'+col+'/'+place);
  }

  public getall(){
    return this.httpClient.get(this.apiServer+'/audit/getall');
  }

}

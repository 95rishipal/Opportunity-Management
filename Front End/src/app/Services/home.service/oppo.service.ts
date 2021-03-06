import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
@Injectable({
  providedIn: 'root'
})

export class OppoService {
  private apiServer = environment.baseUrl;
  constructor(private httpClient: HttpClient) {}

  public getAllOpp() {
      return this.httpClient.get(this.apiServer + '/oppo/getall');
  }

  public del(id) {
      console.log(localStorage.getItem("Email"));
      return this.httpClient.get(this.apiServer + '/oppo/delete/' + id);
  }

  public addOpp(Opportunity) {

      let httpHeaders = new HttpHeaders({
          'Content-Type': 'application/json',
      });

      let options = {
          headers: httpHeaders
      };
      const body = Opportunity;
      return this.httpClient.post(this.apiServer + '/oppo/add', body, options);
  }

  public editOpp(Opportunity) {

      let httpHeaders = new HttpHeaders({
          'Content-Type': 'application/json',
          'Cache-Control': 'no-cache'
      });
      let options = {
          headers: httpHeaders
      };
      const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
      const body = Opportunity
      return this.httpClient.post(this.apiServer + '/oppo/edit/' + Opportunity.oppid, body, options);

  }

}
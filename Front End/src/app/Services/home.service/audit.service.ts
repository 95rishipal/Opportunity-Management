import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from '../../../environments/environment';
@Injectable({
  providedIn: 'root'
})

export class AuditService {
  private apiServer = environment.baseUrl;

  constructor(private httpClient: HttpClient) {}

  public search(col: String, place: String) {
      return this.httpClient.get(this.apiServer + '/audit/search/' + col + '/' + place);
  }

  public getall() {
      return this.httpClient.get(this.apiServer + '/audit/getall');
  }

}
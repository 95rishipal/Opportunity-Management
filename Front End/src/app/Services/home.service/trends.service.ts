import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http";
@Injectable({
  providedIn: 'root'
})
export class TrendsService {

  private apiServer = "http://localhost:8080";
  constructor(private httpClient: HttpClient) {}
  public getAllLang() {
      return this.httpClient.get(this.apiServer + '/trends/language');
  }

  public getAllskills() {
      return this.httpClient.get(this.apiServer + '/trends/skills');
  }

  public getAllUsers() {
      return this.httpClient.get(this.apiServer + '/trends/countusers');
  }

  public getAlldemand() {
      return this.httpClient.get(this.apiServer + '/trends/demand');
  }

  public getAllminxp() {
      return this.httpClient.get(this.apiServer + '/trends/minxp');
  }
}
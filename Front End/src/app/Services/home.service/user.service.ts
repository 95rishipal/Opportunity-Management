import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http";
import { environment } from '../../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServer = environment.baseUrl;
  constructor(private httpClient: HttpClient) {}

  public getAllUser() {
      return this.httpClient.get(this.apiServer + '/user/getall');
  }

  public getCurrentUser() {
      return this.httpClient.get(this.apiServer + '/user/getCurrentUser');
  }
}
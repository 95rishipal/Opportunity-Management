import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})

export class  SearchService{
  private apiServer = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

  public demo(){
    // console.log("Demo");
  }
  public search(col:String, place:String){
    return this.httpClient.get(this.apiServer+'/oppo/search/'+col+'/'+place);
  }

}

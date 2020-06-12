import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalstorageService {

  constructor() { }
  public setAuthData(auth):void{
      localStorage.setItem("Token", auth);
  }

 public setUserData(data):void{
   localStorage.setItem("Email",data);
 }

 public setGid(data):void{
  localStorage.setItem("Gid",data);
}
}

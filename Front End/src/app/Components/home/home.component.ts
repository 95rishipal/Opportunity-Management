import { Component, Input,  OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from '../../Services/login.service/login.service'
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  ImageURL:String;
  public isActive1:boolean;
  public isActive2:boolean;
  public isActive3:boolean;
  public isClicked:boolean[];
  constructor( private router: Router, private loginservice: LoginService) { }
  Email : String;
  
  ngOnInit(): void {
    this.loginservice.check();
    this.Email = localStorage.getItem('Email');
    console.log(this.ImageURL);
    this.ImageURL = localStorage.getItem('ImageURL');
    this.isActive2=true;
  }
  public logout(){
    console.log("Logout");
    alert("Thank you!!!");
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  public onClicked(data){
    let i:number;
    this.isActive1=false;
    this.isActive2=false;
    this.isActive3=false;
    if(data=='btn1'){
      this.isActive1=true;
    }
    if(data=='btn2'){
      this.isActive2=true;
    }
    if(data=='btn3'){
      this.isActive3=true;
    }
    
  }
}

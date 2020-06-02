import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from '../../Services/login.service/login.service'
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor( private router: Router, private loginservice: LoginService) { }
  Email : String;
  ngOnInit(): void {
    this.loginservice.check();
    this.Email = localStorage.getItem('Email');
  }
  public logout(){
    console.log("Logout");
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}

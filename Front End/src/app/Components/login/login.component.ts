import { Component, OnInit, ViewChild, ElementRef, TemplateRef} from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { FormGroup, FormBuilder, FormControl, Validators, NgModel  } from '@angular/forms'
import { User } from '../../Models/User.model';
import { Router } from '@angular/router';
import { LoginService } from '../../Services/login.service/login.service'
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  

  auth2: any;
  @ViewChild('loginRef', {static: true }) loginElement: ElementRef;
  myform: FormGroup;
  defaultuser:User = new User(-1,"","","");
  pass:NgModel;
  display:boolean = false;
  @ViewChild('pass', {static: true }) modalExample: TemplateRef<any>;
  
  constructor(private fb: FormBuilder, 
    private http: HttpClient, 
    private LoginService:LoginService,
    private router: Router) { }

  ngOnInit(): void {
    this.googleSDK();
    this.myform = this.fb.group({
      email: new FormControl('', [Validators.required,Validators.email]),
      password: new FormControl('')
    });
    
  }

  signin(data){
          let user:User = new User(-1,data.email,data.password,"");
          console.log(user);
        //   this.LoginService.check(user).subscribe(()=>{
        //     alert("Login Successfully");
        //     this.router.navigateByUrl('/admin');
        // },response => {
        //         if(response.status == 400){ 
        //           alert("Invalid Email Password"); 
        //         }
        //   });
  }
  onsubmit(data){
    console.log(data);
  }


  googleSDK() {
      window['googleSDKLoaded'] = () => {
        window['gapi'].load('auth2', () => {
          this.auth2 = window['gapi'].auth2.init({
            client_id: '339084513627-aabatoudeql4irut7q0pa0npukdh2ghh.apps.googleusercontent.com',
            cookiepolicy: 'single_host_origin',
            scope: 'profile email'
          });
          
        });
      }

      (function(d, s, id){
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) {return;}
        js = d.createElement(s); js.id = id;
        js.src = "https://apis.google.com/js/platform.js?onload=googleSDKLoaded";
        fjs.parentNode.insertBefore(js, fjs);
      }(document, 'script', 'google-jssdk'));
  }

    public prepareLoginButton() {
      this.auth2.attachClickHandler(this.loginElement.nativeElement, {},
        (googleUser) => {
          let profile = googleUser.getBasicProfile();
          console.log('Token || ' + googleUser.getAuthResponse().id_token);
          console.log('ID: ' + profile.getId());
          console.log('Name: ' + profile.getName());
          console.log('Image URL: ' + profile.getImageUrl());
          console.log('Email: ' + profile.getEmail());
          //YOUR CODE HERE
          let user:User= new User(-1,profile.getEmail(),"",profile.getId())
          this.LoginService.check(user).subscribe((data?:String)=>{
              alert("Login Successfully");
              this.router.navigateByUrl('/admin'); 
          }, response => {
                if(response.status == 400){ 
                  this.LoginService.insert(user).subscribe((data?:String)=>{
                    alert("Registered Successfully");
                      this.router.navigateByUrl('/admin'); 
                  }, response=>{
                    if(response.status == 400){ 
                      alert("Notable to register"); 
                    }});
                }
          });
        }, (error) => {
          alert("Email doesn't verified, please try again later.");
        });      
    }

}

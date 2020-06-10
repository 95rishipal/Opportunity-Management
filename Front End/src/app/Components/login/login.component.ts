import { Component, OnInit, ViewChild, ElementRef, TemplateRef, NgZone, Output} from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { FormGroup, FormBuilder, FormControl, Validators, NgModel  } from '@angular/forms'
import { User } from '../../Models/User.model';
import { Router } from '@angular/router';
import { LoginService } from '../../Services/login.service/login.service'
import { LocalstorageService } from '../../Services/localstorage.service.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  auth2: any;
  @ViewChild('loginRef', {static: true }) loginElement: ElementRef;
  myform: FormGroup;
  defaultuser:User = new User(-1,"","","","");
  pass:NgModel;
  display:boolean = false;
  @ViewChild('pass', {static: true }) modalExample: TemplateRef<any>;

  
  constructor(private fb: FormBuilder, 
    private http: HttpClient, 
    private LoginService:LoginService,
    private router: Router,private ngZone: NgZone,
    private localstorageservice:LocalstorageService) { }

  ngOnInit(): void {
    this.googleSDK();
    this.myform = this.fb.group({
      email: new FormControl('', [Validators.required,Validators.email]),
      password: new FormControl('',[Validators.required]),
      name: new FormControl(''),
    });
    
  }
  public navigate(commands: any[]): void {
    this.ngZone.run(() => this.router.navigate(commands)).then();
  }

  googleSDK() {
      window['googleSDKLoaded'] = () => {
        window['gapi'].load('auth2', () => {
          this.auth2 = window['gapi'].auth2.init({
            client_id: '339084513627-aabatoudeql4irut7q0pa0npukdh2ghh.apps.googleusercontent.com',
            cookiepolicy: 'single_host_origin',
            scope: 'profile email'
          });
          this.prepareLoginButton();
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
          // console.log('Token || ' + googleUser.getAuthResponse().id_token);
          // console.log('ID: ' + profile.getId());
          // console.log('Name: ' + profile.getName());
          // console.log('Image URL: ' + profile.getImageUrl());
          // console.log('Email: ' + profile.getEmail());
          //YOUR CODE HERE
          localStorage.setItem('ImageURL', profile.getImageUrl());
          this.localstorageservice.setAuthData(googleUser.getAuthResponse().id_token);
          this.localstorageservice.setUserData(profile.getEmail());
          this.localstorageservice.setGid(profile.getId());
          let user:User= new User(-1,profile.getName(), profile.getEmail(),"",profile.getId())
          this.LoginService.login(user).subscribe((data?:String)=>{
              alert("Login Successfully");
              this.navigate(["/home/Search"]);
          }, response => {
                if(response.status == 409){ 
                  alert("Invalid Email Password.");
                }
          });
        }, (error) => {
          alert("Email doesn't verified, please try again later.");
        });      
    }

}

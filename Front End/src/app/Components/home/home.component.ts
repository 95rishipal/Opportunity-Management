import { Component, Input,  OnInit, ViewChild, TemplateRef } from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from '../../Services/login.service/login.service';
import { MatDialogRef,MatDialog, MatDialogConfig } from  '@angular/material/dialog';
import { UserService } from '../../Services/home.service/user.service';
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
  public User:UserInfo;

  @ViewChild('DisplayDetail') detailTemplate: TemplateRef<any>;
  private detailDialog: MatDialogRef<TemplateRef<any>>;

  @ViewChild('Query') queryTemplate: TemplateRef<any>;
  private queryDialog: MatDialogRef<TemplateRef<any>>;
  
  constructor( private router: Router, public dialog: MatDialog, private loginservice: LoginService, private userService: UserService) { }
  Email : String;
  
  ngOnInit(): void {
    this.loginservice.check();
    this.Email = localStorage.getItem('Email');
    // console.log(this.ImageURL);
    this.ImageURL = localStorage.getItem('ImageURL');
    this.isActive2=true;
  }

  public query(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.restoreFocus = false;
    dialogConfig.autoFocus = false;
    dialogConfig.role = 'dialog';
    dialogConfig.disableClose = true;
    this.queryDialog= this.dialog.open(this.queryTemplate, dialogConfig);
  }

  public Display(){
    this.userService.getCurrentUser().subscribe((data:any)=>{  
      this.User = new UserInfo(data.userid,data.gid,this.ImageURL,data.name,this.Email);
      const dialogConfig = new MatDialogConfig();
      dialogConfig.restoreFocus = false;
      dialogConfig.autoFocus = false;
      dialogConfig.role = 'dialog';
      dialogConfig.disableClose = true;
      this.detailDialog= this.dialog.open(this.detailTemplate, dialogConfig);
    });
  };

  public logout(){
    // console.log("Logout");
    alert("Thank you!!!");
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}

class UserInfo{
  Username:string;
  UserId: string;
  UserImage: string;
  UserGid: string;
  UserEmail: string;
  constructor(id, gid, image, name, email){
    this.UserId = id;
    this.UserGid = gid;
    this.UserImage = image;
    this.Username = name;
    this.UserEmail=email;
  }
}

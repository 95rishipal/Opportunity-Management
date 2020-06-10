import { Component, OnInit, ViewChild, SystemJsNgModuleLoader, TemplateRef } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms'
import { Opportunity } from '../../../Models/Opportunity.model'
import { OppoService } from '../../../Services/home.service/oppo.service'
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
import { NgForm , FormGroupDirective} from '@angular/forms';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import {LoginService} from '../../../Services/login.service/login.service'
import {UserService} from '../../../Services/home.service/user.service'
import {MatSnackBar, MatSnackBarConfig} from '@angular/material/snack-bar';
import { ConfigurableFocusTrap } from '@angular/cdk/a11y';
import {ErrorStateMatcher} from '@angular/material/core';
import {MatSort} from '@angular/material/sort';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}



@Component({
  selector: 'app-oppo',
  templateUrl: './oppo.component.html',
  styleUrls: ['./oppo.component.css']
})

export class OppoComponent implements OnInit {
  oppodata:any;
  userdata:any;
  currentUser:any;
  myform: FormGroup;
  minDate: Date;
  message:string;
  displayedColumns: string[] = ['oppid','userName', 'userEmail', 'description', 'location','endDate', 'skills','minxp','demand','getdetails'];
  dataSource = new MatTableDataSource<Opportunity>();
  matConf = new MatSnackBarConfig();
  matcher = new MyErrorStateMatcher();

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  @ViewChild('AddForm') addTemplate: TemplateRef<any>;
  public addDialog: MatDialogRef<TemplateRef<any>>;

  @ViewChild('EditForm') editTemplate: TemplateRef<any>;
  public editDialog: MatDialogRef<TemplateRef<any>>;

  @ViewChild('DelForm') delTemplate: TemplateRef<any>;
  public delDialog: MatDialogRef<TemplateRef<any>>;

  @ViewChild('snackBarTemplate')
  snackBarTemplate: TemplateRef<any>;
 
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  defaultOpportunity: Opportunity ;


  // ------------------------- Constructor --------------------------------------
  constructor(private fb: FormBuilder,
              private loginservice: LoginService, 
              private http: HttpClient, 
              private OppoService:OppoService,
              public snackBar: MatSnackBar,
              private UserService:UserService, 
              public dialog: MatDialog) {}
  
  // ------------------------- Init Method ---------------------------------------
  ngOnInit(): void {
    this.defaultOpportunity = new Opportunity();
    this.dataSource.sort = this.sort;
    this.myform = this.fb.group({
      oppid: '0',
      description: new FormControl('', [Validators.required]),
      location: new FormControl('',[Validators.required]),
      endDate: new FormControl('',[Validators.required]),
      skills: new FormControl('',[Validators.required]),
      demand: new FormControl('',[Validators.required]),
      minxp: new FormControl('',[Validators.required]),
    });
    this.minDate = new Date();
      this.UserService.getCurrentUser().subscribe((data: any)=>{
        this.currentUser=data;
  
        this.UserService.getAllUser().subscribe((data: any)=>{
          this.userdata=data;
        
          this.OppoService.getAllOpp().subscribe((data: any)=>{
              this.oppodata = data;
              this.dataSource.data = data
              this.dataSource.paginator = this.paginator;
        
          })
        });
      });
      this.matConf.duration=3*1000;
      this.matConf.horizontalPosition = 'end';
      this.matConf.verticalPosition='top';
      this.matConf.announcementMessage="Running"; 
  }

  public yes(){
    
      this.delRecord(this.oppodata);
  }

  public no(){
 
    this.matConf.panelClass = 'red-snackbar';
    this.snackBar.open("Oppertunity Canceled!!",'', this.matConf);
  }
  public opendelDialog(data){
      this.oppodata = data;
      const dialogConfig = new MatDialogConfig();
      dialogConfig.restoreFocus = false;
      dialogConfig.autoFocus = false;
      dialogConfig.role = 'dialog';
      dialogConfig.disableClose = true;
      this.delDialog = this.dialog.open(this.delTemplate, dialogConfig);
  }

  public opendeditDialog(data){
    this.myform = this.fb.group(data);
      const dialogConfig = new MatDialogConfig();
      dialogConfig.restoreFocus = false;
      dialogConfig.autoFocus = false;
      dialogConfig.role = 'dialog';
      dialogConfig.disableClose = true;
      this.editDialog = this.dialog.open(this.editTemplate, dialogConfig);
  }

  public onEdit(data){
    let options = { year: 'numeric', month: 'long', day: 'numeric' };
  
    const momentDate = new Date(data.endDate);
    const dat = ("0" + momentDate.getDate()).slice(-2);
    const month = ("0" + (momentDate.getMonth() + 1)).slice(-2);
    const year = momentDate.getFullYear();
    const presentDate = year+"-"+month+"-"+dat;
  
    data.endDate = presentDate;
    this.OppoService.editOpp(data).subscribe((data) => {
 
   
       this.OppoService.getAllOpp().subscribe((output: any[])=>{
   
        this.dataSource.data = output;
        this.dataSource.paginator = this.paginator;
      });
   
      this.matConf.panelClass = 'green-snackbar';
      this.snackBar.open("Opportunity Updated!!",'', this.matConf);
      this.editDialog.close();
    }) 
   

  }

  public delRecord(data):any{
      this.OppoService.del(parseInt(data.oppid)).subscribe((output)=>{
       
        this.OppoService.getAllOpp().subscribe((data: any[])=>{
   
          this.dataSource.data = data;
          this.dataSource.paginator = this.paginator;
        });
      
      this.matConf.panelClass = 'green-snackbar';
      this.snackBar.open("Opportunity Deleted!!",'', this.matConf);
      });
  }
  public view(){
    this.OppoService.getAllOpp().subscribe((data: any[])=>{
   
      this.dataSource.data = data;
      this.dataSource.paginator = this.paginator;
    });
  }

  
  public openAddDialog(): void {
    this.defaultOpportunity.default();
    this.myform = this.fb.group( this.defaultOpportunity);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.restoreFocus = false;
    dialogConfig.autoFocus = false;
    dialogConfig.role = 'dialog';
    dialogConfig.disableClose = true;
    this.addDialog = this.dialog.open(this.addTemplate, dialogConfig);
    
  }

  public closeAddDialog() {
    this.addDialog.close();
    this.matConf.panelClass = 'red-snackbar';
    this.snackBar.open("Opportunity Canceled!!",'', this.matConf);
  }

  public editAddDialog(form: NgForm) {
    if (!form.valid) {
      return false;
    }
  }
  public onSubmit(data){
    let options = { year: 'numeric', month: 'long', day: 'numeric' };
    

    const momentDate = new Date(data.endDate);
    const dat = ("0" + momentDate.getDate()).slice(-2);
    const month = ("0" + (momentDate.getMonth() + 1)).slice(-2);
    const year = momentDate.getFullYear();
    const presentDate = year+"-"+month+"-"+dat;


    data.endDate = presentDate;
    this.OppoService.addOpp(data).subscribe((data) => {

       this.OppoService.getAllOpp().subscribe((data: any[])=>{

        this.dataSource.data = data;
        this.dataSource.paginator = this.paginator;
      });
 
      this.matConf.panelClass = 'green-snackbar';
      this.snackBar.open("Opportunity Added!!",'', this.matConf);
    }) 

    this.addDialog.close();
  }

  public onCancel(data){ 
    let msg ;
    if(data == 'Add') { this.addDialog.close(); msg="Oppertunity Canceled!!";}
    if(data == 'Edit') { this.editDialog.close(); msg="Opportunity Canceled!!";}
    this.matConf.panelClass = 'red-snackbar';
    this.snackBar.open(msg,'', this.matConf);
  }
}


 
  


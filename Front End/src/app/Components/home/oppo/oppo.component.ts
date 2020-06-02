import { Component, OnInit, ViewChild, SystemJsNgModuleLoader, TemplateRef } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms'
import { Opportunity } from '../../../Models/Opportunity.model'
import { OppoService } from '../../../Services/home.service/oppo.service'
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import {LoginService} from '../../../Services/login.service/login.service'
import {UserService} from '../../../Services/home.service/user.service'
@Component({
  selector: 'app-oppo',
  templateUrl: './oppo.component.html',
  styleUrls: ['./oppo.component.css']
})

export class OppoComponent implements OnInit {
  oppodata:any
  userdata:any
  currentUser:any
  myform: FormGroup;
  minDate: Date;
  displayedColumns: string[] = ['oppid','userName', 'userEmail', 'description', 'location','endDate', 'skills', 'getdetails'];
  dataSource = new MatTableDataSource<Opportunity>();

  
  @ViewChild('AddForm') addTemplate: TemplateRef<any>;
  private addDialog: MatDialogRef<TemplateRef<any>>;

  @ViewChild('EditForm') editTemplate: TemplateRef<any>;
  private editDialog: MatDialogRef<TemplateRef<any>>;

  @ViewChild('DelForm') delTemplate: TemplateRef<any>;
  private delDialog: MatDialogRef<TemplateRef<any>>;
  
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  defaultOpportunity: Opportunity ;

  // ------------------------- Constructor --------------------------------------
  constructor(private fb: FormBuilder,
              private loginservice: LoginService, 
              private http: HttpClient, 
              private OppoService:OppoService,
              private UserService:UserService, 
              public dialog: MatDialog) {}
  
  // ------------------------- Init Method ---------------------------------------
  ngOnInit(): void {
    this.defaultOpportunity = new Opportunity();
    this.myform = this.fb.group({
      oppid: '0',
      description: new FormControl('', [Validators.required, Validators.minLength(4)]),
      location: new FormControl('',[Validators.required]),
      endDate: new FormControl('',[Validators.required]),
      skills: new FormControl('',[Validators.required]),
    });

      this.minDate = new Date();
      this.OppoService.getAllOpp().subscribe((data: any[])=>{
          this.oppodata = data;
          this.dataSource.data = data
          this.dataSource.paginator = this.paginator;
          console.log(this.oppodata);
      })
      this.UserService.getAllUser().subscribe((data: any[])=>{
        this.userdata=data;
        console.log(this.userdata);
      });

      this.UserService.getCurrentUser().subscribe((data: any[])=>{
        this.currentUser=data;
        console.log(this.currentUser);
      });


  }

  public yes(){
      console.log(this.oppodata.oppid);
      this.delRecord(this.oppodata);
  }

  public opendelDialog(data){
      this.oppodata = data;
      const dialogConfig = new MatDialogConfig();
      dialogConfig.restoreFocus = false;
      dialogConfig.autoFocus = false;
      dialogConfig.role = 'dialog';
      this.delDialog = this.dialog.open(this.delTemplate, dialogConfig);
  }

  public opendeditDialog(data){
    this.myform = this.fb.group(data);
      const dialogConfig = new MatDialogConfig();
      dialogConfig.restoreFocus = false;
      dialogConfig.autoFocus = false;
      dialogConfig.role = 'dialog';
      this.editDialog = this.dialog.open(this.editTemplate, dialogConfig);
  }

  public onEdit(data){
    let options = { year: 'numeric', month: 'long', day: 'numeric' };
    console.log(data);
    const momentDate = new Date(data.endDate);
    const dat = ("0" + momentDate.getDate()).slice(-2);
    const month = ("0" + (momentDate.getMonth() + 1)).slice(-2);
    const year = momentDate.getFullYear();
    const presentDate = year+"-"+month+"-"+dat;
    console.log(presentDate);
    data.endDate = presentDate;
    this.OppoService.editOpp(data).subscribe((data) => {
      console.log("Running");
      console.log(data);
       this.OppoService.getAllOpp().subscribe((output: any[])=>{
        console.log(output);
        this.dataSource.data = output;
        this.dataSource.paginator = this.paginator;
      });
      alert("Opportunity Updated!!");
    }) 
    this.editDialog.close();
  }

  public delRecord(data):any{
      this.OppoService.del(parseInt(data.oppid)).subscribe((output)=>{
       
        this.OppoService.getAllOpp().subscribe((data: any[])=>{
          console.log(data);
          this.dataSource.data = data;
          this.dataSource.paginator = this.paginator;
        });
        alert('Deleted Successfully!!');
      });
  }

  
  public openAddDialog(): void {
    this.defaultOpportunity.default();
    this.myform = this.fb.group( this.defaultOpportunity);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.restoreFocus = false;
    dialogConfig.autoFocus = false;
    dialogConfig.role = 'dialog';
    this.addDialog = this.dialog.open(this.addTemplate, dialogConfig);
    
  }

  public closeAddDialog() {
    this.addDialog.close();
  }

  public editAddDialog(form: NgForm) {
    if (!form.valid) {
      return false;
    }
  }
  public onSubmit(data){
    let options = { year: 'numeric', month: 'long', day: 'numeric' };
    
    console.log();
    const momentDate = new Date(data.endDate);
    const dat = ("0" + momentDate.getDate()).slice(-2);
    const month = ("0" + (momentDate.getMonth() + 1)).slice(-2);
    const year = momentDate.getFullYear();
    const presentDate = year+"-"+month+"-"+dat;
    console.log(presentDate);
    // console.log(Date.UTC(year,month,dat));
    data.endDate = presentDate;
    this.OppoService.addOpp(data).subscribe((data) => {
      console.log("Running");
       this.OppoService.getAllOpp().subscribe((data: any[])=>{
        console.log(data);
        this.dataSource.data = data;
        this.dataSource.paginator = this.paginator;
      });
      alert("Opportunity Added!!");
    }) 

    this.addDialog.close();
  }

  public onCancel(data){ 
    if(data == 'Add') this.addDialog.close();
    if(data == 'Edit')  this.editDialog.close();
  }

}


 
  


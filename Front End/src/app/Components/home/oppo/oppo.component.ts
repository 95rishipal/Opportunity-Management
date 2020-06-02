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
@Component({
  selector: 'app-oppo',
  templateUrl: './oppo.component.html',
  styleUrls: ['./oppo.component.css']
})

export class OppoComponent implements OnInit {
  data:any
  myform: FormGroup;
  minDate: Date;
  displayedColumns: string[] = ['oppid', 'description', 'location','endDate', 'skills', 'getdetails'];
  dataSource = new MatTableDataSource<Opportunity>();
  
  @ViewChild('AddForm') addTemplate: TemplateRef<any>;
  private addDialog: MatDialogRef<TemplateRef<any>>;

  @ViewChild('EditForm') editTemplate: TemplateRef<any>;
  private editDialog: MatDialogRef<TemplateRef<any>>;
  
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  defaultOpportunity: Opportunity ;

  // ------------------------- Constructor --------------------------------------
  constructor(private fb: FormBuilder,private loginservice: LoginService, private http: HttpClient, private OppoService:OppoService, public dialog: MatDialog) {}
  
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
      console.log(data);
      this.dataSource.data = data;
      this.dataSource.paginator = this.paginator;
    })
  }

  public opendelDialog(data){
      console.log(data.oppid);
      this.delRecord(data);
      
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


 
  


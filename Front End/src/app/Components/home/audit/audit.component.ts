import { Component, OnInit, ElementRef, ViewChild, TemplateRef} from '@angular/core';
import { AuditService } from '../../../Services/home.service/audit.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
import { Opportunity } from '../../../Models/Opportunity.model'
import {MatSnackBar, MatSnackBarConfig} from '@angular/material/snack-bar';
import {MatSort} from '@angular/material/sort';

@Component({
  selector: 'app-audit',
  templateUrl: './audit.component.html',
  styleUrls: ['./audit.component.css']
})
export class AuditComponent implements OnInit {

  @ViewChild('column') option:ElementRef;
  @ViewChild('query') sql:ElementRef;

  displayedColumns: string[] = ['id', 'date', 'type','userId','userName', 'view'];
  dataSource = new MatTableDataSource<Opportunity>();
  @ViewChild('AddForm') addTemplate: TemplateRef<any>;
  private addDialog: MatDialogRef<TemplateRef<any>>;
  data:any;
  matConf = new MatSnackBarConfig();
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  constructor(private AuditService:AuditService, public dialog: MatDialog,  public snackBar: MatSnackBar) { }
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  ngOnInit(): void {
    this.dataSource.sort = this.sort;
    this.matConf.duration=3*1000;
    this.matConf.horizontalPosition = 'end';
    this.matConf.verticalPosition='top';
    this.matConf.announcementMessage="Running"; 
  }

  public searchM():void{
    
    let col = this.option.nativeElement.value;
    let query = this.sql.nativeElement.value
 
    this.AuditService.search(col,query).subscribe((data: any[])=>{

      if(data.length == 0){
          this.dataSource.data = [];

          this.matConf.panelClass = 'red-snackbar';
          this.snackBar.open("No Result Found!!",'', this.matConf);
      }else{
        this.dataSource.data = data;
        this.dataSource.paginator = this.paginator;
        this.matConf.panelClass = 'green-snackbar';
          this.snackBar.open(data.length+" results found!!",'', this.matConf);
      }
    }, error =>{
      this.dataSource.data=[];
       this.matConf.panelClass = 'red-snackbar';
      this.snackBar.open("[Server] Somthing went wrong, Try Again!!",'', this.matConf);
    });
  }

  public view(data){

    this.data= data;
    this.openAddDialog();
  }

  public openAddDialog(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.restoreFocus = false;
    dialogConfig.autoFocus = false;
    dialogConfig.role = 'dialog';
    dialogConfig.disableClose = true;
    this.addDialog = this.dialog.open(this.addTemplate, dialogConfig);
    
  }
  public getAll(){
    this.AuditService.getall().subscribe((data: any[])=>{

      if(data.length == 0){
          this.dataSource.data = [];
          this.matConf.panelClass = 'red-snackbar';
          this.snackBar.open("No Result Found!!",'', this.matConf);
      }else{
        this.dataSource.data = data;
        this.dataSource.paginator = this.paginator;
        this.matConf.panelClass = 'green-snackbar';
          this.snackBar.open(data.length+" results found!!",'', this.matConf);
      }
    }, error =>{
      this.dataSource.data=[];
      this.matConf.panelClass = 'red-snackbar';
      this.snackBar.open("[Server] Somthing went wrong, Try Again!!",'', this.matConf);
    });
  }
}






import { Component, OnInit, ElementRef, ViewChild, TemplateRef} from '@angular/core';
import { SearchService } from '../../../Services/home.service/search.service.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
import { Opportunity } from '../../../Models/Opportunity.model'
import {MatSnackBar, MatSnackBarConfig} from '@angular/material/snack-bar';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  @ViewChild('column') option:ElementRef;
  @ViewChild('query') sql:ElementRef;
  displayedColumns: string[] = ['oppid', 'description', 'location','endDate', 'skills'];
  dataSource = new MatTableDataSource<Opportunity>();
  @ViewChild('AddForm') addTemplate: TemplateRef<any>;
  private addDialog: MatDialogRef<TemplateRef<any>>;
  matConf = new MatSnackBarConfig();
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  defaultOpportunity: Opportunity ;
  constructor(private SearchService:SearchService,  public snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.matConf.duration=3*1000;
    this.matConf.horizontalPosition = 'end';
    this.matConf.verticalPosition='top';
    this.matConf.announcementMessage="Running"; 
  }

  public searchM():void{
    let col = this.option.nativeElement.value;
    let query = this.sql.nativeElement.value
    // console.log(col);
    // console.log(query);
    this.SearchService.search(col,query).subscribe((data: any[])=>{
      // console.log(data);
      if(data.length == 0){
          this.dataSource.data = [];
          // alert("No Result Found!!");
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
      // alert('[Server] Somthing went wrong, Try Again!!');
      this.matConf.panelClass = 'red-snackbar';
      this.snackBar.open("[Server] Somthing went wrong, Try Again!!",'', this.matConf);
    });
  }
}

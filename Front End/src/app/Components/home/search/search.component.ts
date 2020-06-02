import { Component, OnInit, ElementRef, ViewChild, TemplateRef} from '@angular/core';
import { SearchService } from '../../../Services/home.service/search.service.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
import { Opportunity } from '../../../Models/Opportunity.model'
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
  
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  defaultOpportunity: Opportunity ;
  constructor(private SearchService:SearchService) { }

  ngOnInit(): void {
    
  }

  public searchM():void{
    let col = this.option.nativeElement.value;
    let query = this.sql.nativeElement.value
    console.log(col);
    console.log(query);
    this.SearchService.search(col,query).subscribe((data: any[])=>{
      console.log(data);
      if(data.length == 0){
          this.dataSource.data = [];
          alert("No Result Found!!");
      }else{
        this.dataSource.data = data;
        this.dataSource.paginator = this.paginator;
      }
    }, error =>{
      this.dataSource.data=[];
      alert('[Server] Somthing went wrong, Try Again!!');
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { MatTab } from '@angular/material/tabs'
import {MatSnackBar} from '@angular/material/snack-bar';
import { TrendsService } from '../../../Services/home.service/trends.service'
@Component({
  selector: 'app-trends',
  templateUrl: './trends.component.html',
  styleUrls: ['./trends.component.css']
})
export class TrendsComponent implements OnInit {
  


  view: any[] = [600, 400];
  // options for the chart
  showXAxis = true;
  showYAxis = true;
  gradient = true;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel1 = 'Location';
  xAxisLabel2 = 'Skills';
  showYAxisLabel = true;
  yAxisLabel = 'Count';
  timeline = true;
  colorScheme= "vivid";

  public lang;
  public skills;
  public NoUser;
  public NoSkills;
  public NoLocations;
  public NoOpportunity;
  showLabels = true;
  constructor(private _snackBar: MatSnackBar, private trendService: TrendsService) {}
  ngOnInit(){
    this.trendService.getAllLang().subscribe((data)=>{
      this.lang = data;
    },error=>{

    });
    this.trendService.getAllskills().subscribe((data)=>{
      this.skills = data;
    },error=>{

    });

    this.trendService.getAllUsers().subscribe((data:any[])=>{
      
      console.log(data);
      this.NoUser = data;
      this.NoOpportunity = data;
      console.log(this.NoUser);
    },error=>{

    });
  }
 
}


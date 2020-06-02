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
  

  title = 'Location';
  view: any[] = [600, 400];
  // options for the chart
  showXAxis = true;
  showYAxis = true;
  gradient = true;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'Location';
  showYAxisLabel = true;
  yAxisLabel = 'Count';
  timeline = true;
  colorScheme = {
    domain: ['#9370DB', '#87CEFA','#FA8072', '#FF7F50', '#90EE90', '#97A0EE', '#97B0EE','#A7A0EE', '#9AA0EE']
  };
  // 
  public lang;
  public skills;
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
  }
  onSelect(data){
      console.log(data);
      this.openSnackBar(data,data)
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

// public single = [
//   {
//     "name": "China",
//     "value": 2243772
//   },
//   {
//     "name": "USA",
//     "value": 1126000
//   },
//   {
//     "name": "Norway",
//     "value": 296215
//   },
//   {
//     "name": "Japan",
//     "value": 257363
//   },
//   {
//     "name": "Germany",
//     "value": 196750
//   },
//   {
//     "name": "France",
//     "value": 204617
//   }
// ];

}


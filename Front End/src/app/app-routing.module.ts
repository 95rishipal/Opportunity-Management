import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './Components/home/home.component'
import { SearchComponent } from './Components/home/search/search.component'
import { TrendsComponent } from './Components/home/trends/trends.component'
import { OppoComponent } from './Components/home/oppo/oppo.component'
import { LoginComponent } from './Components/login/login.component'
import {AuditComponent} from '../app/Components/home/audit/audit.component'
 
const routes: Routes = [ 
  { path: 'home', component: HomeComponent,
children: [
        { path: '', redirectTo: '/home/search', pathMatch: 'full' },
        { path: 'opportunity', component: OppoComponent },
        { path: 'search', component: SearchComponent },
        { path: 'trends', component: TrendsComponent },
        { path: 'audit', component: AuditComponent }
      ] },
      { path: 'login', component: LoginComponent}, 
      {path: '**', redirectTo: 'home'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

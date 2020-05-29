import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './Components/home/home.component'
import { SearchComponent } from './Components/home/search/search.component'
import { TrendsComponent } from './Components/home/trends/trends.component'
import { OppoComponent } from './Components/home/oppo/oppo.component'
import { MyprofileComponent } from './Components/home/myprofile/myprofile.component'
import { LoginComponent } from './Components/login/login.component'
 
const routes: Routes = [ { path: 'home', component: HomeComponent,
children: [
      { path: '', redirectTo: 'opportunity', pathMatch: 'full' },
      { path: 'opportunity', component: OppoComponent },
      { path: 'trends', component: TrendsComponent },
      { path: 'search', component: SearchComponent },
      { path: 'myprofile', component: MyprofileComponent },
      ] },
      { path: 'login', component: LoginComponent}, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

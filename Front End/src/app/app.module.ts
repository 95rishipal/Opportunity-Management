import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './Components/home/home.component';
import { OppoComponent } from './Components/home/oppo/oppo.component';
import { SearchComponent } from './Components/home/search/search.component';
import { TrendsComponent } from './Components/home/trends/trends.component';
import { LoginComponent } from './Components/login/login.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { MyprofileComponent } from './Components/home/myprofile/myprofile.component';
import { MatListModule} from '@angular/material/list'
import { MatButtonModule} from '@angular/material/button'
import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table'
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule  }  from  '@angular/material/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import {  MatCardModule } from '@angular/material/card';
import { HTTP_INTERCEPTORS } from '@angular/common/http'
import { AuthInterceptor } from './Interceptors/AuthInterceptor';
import { MatTabsModule } from '@angular/material/tabs'
import { NgxChartsModule } from '@swimlane/ngx-charts';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { MatMenuModule } from '@angular/material/menu'

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    OppoComponent,
    SearchComponent,
    TrendsComponent,
    LoginComponent,
    MyprofileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule, NgxChartsModule,MatMenuModule,
    MatToolbarModule,ReactiveFormsModule,FormsModule,MatCardModule,MatTabsModule,MatSnackBarModule,
    MatIconModule,MatListModule,MatFormFieldModule,MatDatepickerModule,MatInputModule, MatDatepickerModule,MatNativeDateModule,
    MatButtonModule,HttpClientModule,MatTableModule,MatPaginatorModule,MatDialogModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, 
      useClass: AuthInterceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

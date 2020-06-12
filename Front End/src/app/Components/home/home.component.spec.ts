import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HomeComponent } from './home.component';
import { HttpClientModule } from '@angular/common/http'
import { RouterTestingModule } from '@angular/router/testing'
import { MatMenuModule } from '@angular/material/menu'
import { MatDialogModule } from '@angular/material/dialog';
import { Router, Routes, RouterModule } from '@angular/router';
import { SearchComponent } from '../../Components/home/search/search.component'
import { TrendsComponent } from '../../Components/home/trends/trends.component'
import { OppoComponent } from '../../Components/home/oppo/oppo.component'
import { LoginComponent } from '../../Components/login/login.component'
import {AuditComponent} from '../../Components/home/audit/audit.component'
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { By } from '@angular/platform-browser';
import { UserService } from 'src/app/Services/home.service/user.service';
import { of } from 'rxjs'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
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




describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  let router: Router;
  let userService: UserService;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports:[RouterModule.forRoot(routes),
        RouterTestingModule.withRoutes([]), HttpClientModule,  MatMenuModule, BrowserAnimationsModule,MatSnackBarModule,MatDialogModule],
      declarations: [ HomeComponent ],
      providers:[UserService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    userService = TestBed.get(UserService);
    fixture.detectChanges();
    router = TestBed.get(Router);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render the trend button',() => {
    fixture.detectChanges();
    let btn = fixture.debugElement.query(By.css('#trend')).nativeElement;
    btn.click();
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(location.pathname).toEqual('/home/search');
    });
  });

  it('should render the search button',() => {
    fixture.detectChanges();
    let btn = fixture.debugElement.query(By.css('#search')).nativeElement;
    btn.click();
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(location.pathname).toEqual('/home/search');
    });
  });


  it('should render the Opportunity button',() => {
    fixture.detectChanges();
    let btn = fixture.debugElement.query(By.css('#opportunity')).nativeElement;
    btn.click();
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(location.pathname).toEqual('/home/search');
    });
  });

  it('should render the Opportunity button',() => {
    component.query();
  });
  it('should render the Opportunity button',() => {
    spyOn(userService,'getCurrentUser').and.returnValue(of([]));
    component.Display();
    expect(userService.getCurrentUser).toHaveBeenCalled();
  });

  it('should render the Opportunity button',() => {
    component.logout();
  });


});

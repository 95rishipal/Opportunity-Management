import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { LoginService } from '../../Services/login.service/login.service';
import { of , Observable} from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, HttpClientModule, RouterTestingModule, BrowserAnimationsModule],
      declarations: [ LoginComponent ],
      providers:[LoginService],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });


  it('Should Create Login Component', () => {
    expect(component).toBeTruthy();
  });


  it('Should Able to Initialize Google SDK', () => {
    spyOn(component, 'googleSDK').and.callThrough();
    spyOn(component, 'prepareLoginButton').and.callThrough();
    component.ngOnInit();
    expect(component.googleSDK).toHaveBeenCalled();
    expect(component.googleSDK).toHaveBeenCalled();
  });
});

class loginServiceStub{
  public login(data):Observable<any>{
    let result: [{
      name: 'Demo',
      token: '12345'
    }];
    return of(result);
  }
}

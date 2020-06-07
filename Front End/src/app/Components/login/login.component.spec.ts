import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { LoginService } from '../../Services/login.service/login.service';
import { of , Observable} from 'rxjs';
describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, HttpClientModule, RouterTestingModule],
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


  it('should create', () => {
    expect(component).toBeTruthy();
  });


  // it('should able to do signin', () => {
  //   let loginService: LoginService;
  //   jasmine.createSpy('login').and.callThrough();
  //   // component.prepareLoginButton();
  //   expect(loginService.login).toHaveBeenCalled();
  // });

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

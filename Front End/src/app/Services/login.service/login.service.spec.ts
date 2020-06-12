import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing'
import { LoginService } from './login.service';
import { HttpClient } from '@angular/common/http';
import { of } from 'rxjs'
describe('LoginService', () => {
  let service: LoginService;
  let httpClient : HttpClient;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientModule, RouterTestingModule]
    });
    service = TestBed.inject(LoginService);
    httpClient = TestBed.get(HttpClient);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('Should Able To Call Login User API', () => {
    spyOn(httpClient,'post').and.returnValue(of([]));
    service.login([]);
    expect(service.login).toBeTruthy();
    expect(httpClient.post).toHaveBeenCalled();
  });
});

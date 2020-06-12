import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http'
import { UserService } from './user.service';
import { HttpClient } from '@angular/common/http';
import { of } from 'rxjs';
describe('UserService', () => {
  let service: UserService;
  let httpClient: HttpClient;
  beforeEach(() => {
      TestBed.configureTestingModule({
          imports: [HttpClientModule]
      });
      service = TestBed.inject(UserService);
      httpClient = TestBed.get(HttpClient);
  });

  it('Should Able To Create User Service', () => {
      expect(service).toBeTruthy();
  });

  it('Should Able To Call Get All User API', () => {
      spyOn(httpClient, 'get').and.returnValue( of ([]));
      service.getAllUser();
      expect(service.getAllUser).toBeTruthy();
      expect(httpClient.get).toHaveBeenCalled();
  });

  it('Should Able To Call Current User API', () => {
      spyOn(httpClient, 'get').and.returnValue( of ([]));
      service.getCurrentUser();
      expect(service.getCurrentUser).toBeTruthy();
      expect(httpClient.get).toHaveBeenCalled();
  });
});
import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http'
import { OppoService } from './oppo.service';
import { HttpClient } from '@angular/common/http';
import { of } from 'rxjs'
describe('OppoService', () => {
  let service: OppoService;
  let httpClient : HttpClient;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientModule]
    });
    service = TestBed.inject(OppoService);
    httpClient = TestBed.get(HttpClient); 
  });

  it('Should Create Opportunity Service', () => {
    expect(service).toBeTruthy();
  });

  it('Should Able To Call Get All Oppo. API', () => {
    spyOn(httpClient,'get').and.returnValue(of([]));
    service.getAllOpp();
    expect(service.getAllOpp).toBeTruthy();
    expect(httpClient.get).toHaveBeenCalled();
  });

  it('Should Able To Call del Oppo. API', () => {
    spyOn(httpClient,'delete').and.returnValue(of([]));
    service.del(1);
    expect(service.del).toBeTruthy();
    expect(httpClient.delete).toHaveBeenCalled();
  });

  it('Should Able To Call Edit Oppo. API', () => {
    spyOn(httpClient,'post').and.returnValue(of([]));
    service.editOpp([]);
    expect(service.editOpp).toBeTruthy();
    expect(httpClient.post).toHaveBeenCalled();
  });

  it('Should Able To Call Add Oppo. API', () => {
    spyOn(httpClient,'post').and.returnValue(of([]));
    service.addOpp([]);
    expect(service.addOpp).toBeTruthy();
    expect(httpClient.post).toHaveBeenCalled();
  });

});

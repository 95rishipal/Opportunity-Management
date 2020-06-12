import { TestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { AuditService } from './audit.service';
import { of } from 'rxjs';

describe('AuditService', () => {
  let service: AuditService;
  let httpClient: HttpClient;
  beforeEach(() => {
      TestBed.configureTestingModule({
          imports: [HttpClientModule]
      });
      service = TestBed.inject(AuditService);
      httpClient = TestBed.get(HttpClient);
  });

  it('Should Create Audit Service', () => {
      expect(service).toBeTruthy();
  });

  it('Should Able To Call Search API', () => {
      spyOn(httpClient, "get").and.returnValue( of ([]))
      service.search("col", "place")
      expect(service.search).toBeTruthy();
      expect(httpClient.get).toHaveBeenCalled();
  });

  it('Should Able To Call Get Audit API', () => {
      spyOn(httpClient, "get").and.returnValue( of ([]))
      service.getall()
      expect(service.getall).toBeTruthy();
      expect(httpClient.get).toHaveBeenCalled();
  });

});
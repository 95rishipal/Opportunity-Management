import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { AuditService } from './audit.service';

describe('AuditService', () => {
  let service: AuditService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientModule]
    });
    service = TestBed.inject(AuditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should be working search Audit', () => {
    expect(service.search).toBeTruthy();
  });

  it('should be working getall Audit', () => {
    expect(service.getall).toBeTruthy();
  });

});

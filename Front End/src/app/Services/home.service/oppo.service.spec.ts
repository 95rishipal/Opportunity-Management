import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http'
import { OppoService } from './oppo.service';

describe('OppoService', () => {
  let service: OppoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientModule]
    });
    service = TestBed.inject(OppoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

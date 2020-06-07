import { TestBed } from '@angular/core/testing';
import { HttpClientModule  } from '@angular/common/http'
import { TrendsService } from './trends.service';

describe('TrendsService', () => {
  let service: TrendsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientModule]
    }).compileComponents();
    service = TestBed.inject(TrendsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

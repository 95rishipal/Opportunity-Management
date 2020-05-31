import { TestBed } from '@angular/core/testing';

import { Search.ServiceService } from './search.service.service';

describe('Search.ServiceService', () => {
  let service: Search.ServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Search.ServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { OppoService } from './oppo.service';

describe('OppoService', () => {
  let service: OppoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OppoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

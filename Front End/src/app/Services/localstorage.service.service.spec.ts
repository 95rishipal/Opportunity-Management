import { TestBed } from '@angular/core/testing';

import { Localstorage.ServiceService } from './localstorage.service.service';

describe('Localstorage.ServiceService', () => {
  let service: Localstorage.ServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Localstorage.ServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

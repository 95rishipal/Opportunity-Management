import { TestBed } from '@angular/core/testing';
import { SearchService } from '../../Services/home.service/search.service.service';
import { HttpClientModule } from '@angular/common/http'

describe('Search.ServiceService', () => {
  let service: SearchService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports : [ HttpClientModule ],
    });
    service = TestBed.inject(SearchService);

  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

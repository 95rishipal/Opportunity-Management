import { TestBed } from '@angular/core/testing';
import { SearchService } from '../../Services/home.service/search.service.service';
import { HttpClientModule } from '@angular/common/http'
import { HttpClient } from '@angular/common/http';
import { of } from 'rxjs'
describe('Search.ServiceService', () => {
  let service: SearchService;
  let httpClient : HttpClient;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports : [ HttpClientModule ],
    });
    service = TestBed.inject(SearchService);
    httpClient = TestBed.get(HttpClient);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('Should Able To Call Search Oppo API', () => {
    spyOn(httpClient,'get').and.returnValue(of([]));
    service.search("col","user");
    expect(service.search).toBeTruthy();
    expect(httpClient.get).toHaveBeenCalled();
  });
});

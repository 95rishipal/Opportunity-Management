import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http'; 
import { SearchComponent } from './search.component';
import { MatSnackBarModule } from '@angular/material/snack-bar'
import { SearchService } from '../../../Services/home.service/search.service.service';
import { of, Observable } from 'rxjs';
describe('SearchComponent', () => {
  let component: SearchComponent;
  let fixture: ComponentFixture<SearchComponent>;
  let searchService : SearchService;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule, MatSnackBarModule],
      declarations: [ SearchComponent ],
      providers:[SearchService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchComponent);
    searchService = TestBed.get(SearchService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should working get all Call', () => {
    spyOn(searchService,'search').and.returnValue(of([]));
    component.searchM();
    expect(searchService.search).toHaveBeenCalled();
});
  
});

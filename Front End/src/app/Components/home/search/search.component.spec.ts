import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http'; 
import { SearchComponent } from './search.component';
import { MatSnackBarModule } from '@angular/material/snack-bar'
import { SearchService } from '../../../Services/home.service/search.service.service';
import { of, Observable, throwError } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
describe('SearchComponent', () => {
  let component: SearchComponent;
  let fixture: ComponentFixture < SearchComponent > ;
  let searchService: SearchService;
  beforeEach(async(() => {
      TestBed.configureTestingModule({
              imports: [HttpClientModule, MatSnackBarModule, BrowserAnimationsModule],
              declarations: [SearchComponent],
              providers: [SearchService]
          })
          .compileComponents();
  }));

  beforeEach(() => {
      fixture = TestBed.createComponent(SearchComponent);
      searchService = TestBed.get(SearchService);
      component = fixture.componentInstance;
      fixture.detectChanges();
  });

  it('Should Create Search Component', () => {
      expect(component).toBeTruthy();
  });

  it('Should Call Search Method and Return No Data', () => {
      spyOn(searchService, 'search').and.returnValue( of ([]));
      component.searchM();
      expect(searchService.search).toHaveBeenCalled();
      expect(component.dataSource.data).toEqual([]);
  });

  it('Should Call Search Method and Return with Data', () => {
      spyOn(searchService, 'search').and.returnValue( of ([name]));
      component.searchM();
      expect(searchService.search).toHaveBeenCalled();
      expect(component.dataSource.data).toEqual([name]);
  });

  it('Should Call Search Method and Throw Error', () => {
      spyOn(searchService, 'search').and.returnValue(throwError("No Data Found"));
      component.searchM();
      expect(searchService.search).toHaveBeenCalled();
  });

});
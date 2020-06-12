import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MatSnackBarModule  } from '@angular/material/snack-bar'
import { TrendsComponent } from './trends.component';
import { HttpClientModule } from '@angular/common/http';
import { TrendsService } from 'src/app/Services/home.service/trends.service';
import { of, Observable, throwError } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
describe('TrendsComponent', () => {
  let component: TrendsComponent;
  let fixture: ComponentFixture < TrendsComponent > ;
  let trendSearch: TrendsService;
  beforeEach(async(() => {
      TestBed.configureTestingModule({
              imports: [MatSnackBarModule, HttpClientModule, BrowserAnimationsModule],
              declarations: [TrendsComponent],
              providers: [TrendsService]
          })
          .compileComponents();
  }));

  beforeEach(() => {
      fixture = TestBed.createComponent(TrendsComponent);
      trendSearch = TestBed.get(TrendsService);
      component = fixture.componentInstance;
      fixture.detectChanges();
  });

  it('Should Create Trend', () => {
      expect(component).toBeTruthy();
  });

  it('Should Call Get Language Count and Return No Data', () => {
      spyOn(trendSearch, 'getAllLang').and.returnValue( of ([]));
      component.ngOnInit();
      expect(trendSearch.getAllLang).toHaveBeenCalled();
      expect(component.lang).toEqual([]);
  });

  it('Should Call Get Language Count and Return Some Data', () => {
      spyOn(trendSearch, 'getAllLang').and.returnValue( of ([name]));
      component.ngOnInit();
      expect(trendSearch.getAllLang).toHaveBeenCalled();
      expect(component.lang).toEqual([name]);
  });

  it('Should Call Get Language Count and Throw Error', () => {
      spyOn(trendSearch, 'getAllLang').and.returnValue(throwError("No Data Present"));
      component.ngOnInit();
      expect(trendSearch.getAllLang).toHaveBeenCalled();
  });

  it('Should Call Get User Count  and Return No Data', () => {
      spyOn(trendSearch, 'getAllUsers').and.returnValue( of ([]));
      component.ngOnInit();
      expect(trendSearch.getAllUsers).toHaveBeenCalled();
      expect(component.NoUser).toEqual([]);
  });

  it('Should Call Get User  Count  and Return Some Data', () => {
      spyOn(trendSearch, 'getAllUsers').and.returnValue( of ([name]));
      component.ngOnInit();
      expect(trendSearch.getAllUsers).toHaveBeenCalled();
      expect(component.NoUser).toEqual([name]);
  });

  it('Should Call Get User  Count  and Throw Error', () => {
      spyOn(trendSearch, 'getAllUsers').and.returnValue(throwError("No Data Present"));
      component.ngOnInit();
      expect(trendSearch.getAllUsers).toHaveBeenCalled();
  });

  it('Should Call Get Demand Count and Return No Data', () => {
      spyOn(trendSearch, 'getAlldemand').and.returnValue( of ([]));
      component.ngOnInit();
      expect(trendSearch.getAlldemand).toHaveBeenCalled();
      expect(component.demand).toEqual([]);
  });

  it('Should Call Get Demand Count and Return Some Data', () => {
      spyOn(trendSearch, 'getAlldemand').and.returnValue( of ([name]));
      component.ngOnInit();
      expect(trendSearch.getAlldemand).toHaveBeenCalled();
      expect(component.demand).toEqual([name]);
  });

  it('Should Call Get Demand Count and Throw Error', () => {
      spyOn(trendSearch, 'getAlldemand').and.returnValue(throwError("No Data Present"));
      component.ngOnInit();
      expect(trendSearch.getAlldemand).toHaveBeenCalled();
  });

  it('Should Call Get Min XP Count and Return No Data', () => {
      spyOn(trendSearch, 'getAllminxp').and.returnValue( of ([]));
      component.ngOnInit();
      expect(trendSearch.getAllminxp).toHaveBeenCalled();
      expect(component.minxp).toEqual([]);
  });

  it('Should Call Get Min XP Count and Return Some Data', () => {
      spyOn(trendSearch, 'getAllminxp').and.returnValue( of ([name]));
      component.ngOnInit();
      expect(trendSearch.getAllminxp).toHaveBeenCalled();
      expect(component.minxp).toEqual([name]);
  });

  it('Should Call Get Min XP Count and Throw Error', () => {
      spyOn(trendSearch, 'getAllminxp').and.returnValue(throwError("No Data Present"));
      component.ngOnInit();
      expect(trendSearch.getAllminxp).toHaveBeenCalled();;
  });


  it('Should Call Get Skills Count and Return No Data', () => {
      spyOn(trendSearch, 'getAllskills').and.returnValue( of ([]));
      component.ngOnInit();
      expect(trendSearch.getAllskills).toHaveBeenCalled();
      expect(component.skills).toEqual([]);
  });

  it('Should Call Get Skills Count and Return Some Data', () => {
      spyOn(trendSearch, 'getAllskills').and.returnValue( of ([name]));
      component.ngOnInit();
      expect(trendSearch.getAllskills).toHaveBeenCalled();
      expect(component.skills).toEqual([name]);
  });

  it('Should Call Get Skills Count and Throw Error', () => {
      spyOn(trendSearch, 'getAllskills').and.returnValue(throwError("No Data Present"));
      component.ngOnInit();
      expect(trendSearch.getAllskills).toHaveBeenCalled();;
  });

});

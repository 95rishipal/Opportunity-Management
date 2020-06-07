import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MatSnackBarModule  } from '@angular/material/snack-bar'
import { TrendsComponent } from './trends.component';
import { HttpClientModule } from '@angular/common/http';
import { TrendsService } from 'src/app/Services/home.service/trends.service';
import { of, Observable } from 'rxjs';
describe('TrendsComponent', () => {
  let component: TrendsComponent;
  let fixture: ComponentFixture<TrendsComponent>;
  let trendSearch : TrendsService;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ MatSnackBarModule, HttpClientModule ],
      declarations: [ TrendsComponent ],
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

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should working get lang Call', () => {
    spyOn(trendSearch,'getAllLang').and.returnValue(of());
    component.ngOnInit();
    expect(trendSearch.getAllLang).toHaveBeenCalled();
});

// it('should working get user Call', () => {
//   spyOn(trendSearch,'getAllUsers').and.returnValue(of());
//   component.ngOnInit();
//   expect(trendSearch.getAllUsers).toHaveBeenCalled();
// });

it('should working get  demand Call', () => {
  spyOn(trendSearch,'getAlldemand').and.returnValue(of());
  component.ngOnInit();
  expect(trendSearch.getAlldemand).toHaveBeenCalled();
});

// it('should working get minxp Call', () => {
//   spyOn(trendSearch,'getAllminxp').and.returnValue(of());
//   component.ngOnInit();
//   expect(trendSearch.getAllminxp).toHaveBeenCalled();
// });


// it('should working get skills Call', () => {
//   spyOn(trendSearch,'getAllskills').and.returnValue(of());
//   component.ngOnInit();
//   expect(trendSearch.getAllskills).toHaveBeenCalled();
// });

});

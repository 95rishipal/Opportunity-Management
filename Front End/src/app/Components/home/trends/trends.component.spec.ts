import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MatSnackBarModule  } from '@angular/material/snack-bar'
import { TrendsComponent } from './trends.component';
import { HttpClientModule } from '@angular/common/http';
import { TrendsService } from 'src/app/Services/home.service/trends.service';
import { of, Observable } from 'rxjs';
describe('TrendsComponent', () => {
  let component: TrendsComponent;
  let fixture: ComponentFixture<TrendsComponent>;
  let treandSearch : TrendsService;
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
    treandSearch = TestBed.get(TrendsService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should working get lang Call', () => {
    spyOn(treandSearch,'getAllLang').and.returnValue(of());
    component.ngOnInit();
    expect(treandSearch.getAllLang).toHaveBeenCalled();
});

// it('should working get user Call', () => {
//   spyOn(treandSearch,'getAllUsers').and.returnValue(of());
//   component.ngOnInit();
//   expect(treandSearch.getAllUsers).toHaveBeenCalled();
// });

it('should working get  demand Call', () => {
  spyOn(treandSearch,'getAlldemand').and.returnValue(of());
  component.ngOnInit();
  expect(treandSearch.getAlldemand).toHaveBeenCalled();
});

// it('should working get minxp Call', () => {
//   spyOn(treandSearch,'getAllminxp').and.returnValue(of());
//   component.ngOnInit();
//   expect(treandSearch.getAllminxp).toHaveBeenCalled();
// });


// it('should working get skills Call', () => {
//   spyOn(treandSearch,'getAllskills').and.returnValue(of());
//   component.ngOnInit();
//   expect(treandSearch.getAllskills).toHaveBeenCalled();
// });

});

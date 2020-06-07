import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import{TemplateRef} from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http';
import { OppoComponent } from './oppo.component';
import { RouterTestingModule } from '@angular/router/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { OppoService } from 'src/app/Services/home.service/oppo.service';
import { of } from 'rxjs';
import { By } from '@angular/platform-browser'

describe('OppoComponent', () => {
  let component: OppoComponent;
  let fixture: ComponentFixture<OppoComponent>;
  let oppoService: OppoService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, HttpClientModule, RouterTestingModule, MatSnackBarModule, MatDialogModule],
      declarations: [ OppoComponent ],
      providers: [ OppoService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OppoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    oppoService = TestBed.get(OppoService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should working del oppo Call', () => {
    spyOn(oppoService,'del').and.returnValue(of([]));
    component.delRecord(1);
    expect(oppoService.del).toHaveBeenCalled();
  });

  it('should working get all oppo Call', () => {
    spyOn(oppoService,'getAllOpp').and.returnValue(of([]));
    component.view();
    expect(oppoService.getAllOpp).toHaveBeenCalled();
  });


  it('should working edit oppo Call', () => {
    spyOn(oppoService,'editOpp').and.returnValue(of([]));
    let data:any[] =[{
      endDate:'2020-04-05'
    }];
    component.onEdit(data);
    expect(oppoService.editOpp).toHaveBeenCalled();
  });

});

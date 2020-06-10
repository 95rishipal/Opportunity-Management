import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import{TemplateRef} from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http';
import { OppoComponent } from './oppo.component';
import { RouterTestingModule } from '@angular/router/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { OppoService } from 'src/app/Services/home.service/oppo.service';
import { of } from 'rxjs';
import { By } from '@angular/platform-browser'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


describe('OppoComponent', () => {
  let component: OppoComponent;
  let fixture: ComponentFixture<OppoComponent>;
  let oppoService: OppoService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule,BrowserAnimationsModule, HttpClientModule, RouterTestingModule, MatSnackBarModule, MatDialogModule],
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

  it('dialog box open', ()=>{
      let btn = fixture.debugElement.query(By.css('#CreateOpportunity'));
      
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

  it('is form valid when empty', ()=>{
      expect(component.myform.valid).toBeFalsy();
  }); 

  it('is form valid when filled', ()=>{
    let description = component.myform.controls['description'];
    let location = component.myform.controls['location'];
    let endDate = component.myform.controls['endDate'];
    let skills = component.myform.controls['skills'];
    let demand = component.myform.controls['demand'];
    let minxp = component.myform.controls['minxp'];
    description.setValue("Required");
    location.setValue("Pune");
    endDate.setValue("2020-05-19");
    skills.setValue("Java");
    demand.setValue(0);
    minxp.setValue(1);
    expect(component.myform.valid).toBeTruthy();
});

it('is form error msg defined', ()=>{
  let description = component.myform.controls['description'];
  let location = component.myform.controls['location'];
  let endDate = component.myform.controls['endDate'];
  let skills = component.myform.controls['skills'];
  let demand = component.myform.controls['demand'];
  let minxp = component.myform.controls['minxp'];
  expect(component.myform.valid).toBeFalsy();
  expect(description.errors['required']).toBeDefined();
  expect(location.errors['required']).toBeDefined();
  expect(endDate.errors['required']).toBeDefined();
  expect(skills.errors['required']).toBeDefined();
  expect(demand.errors['required']).toBeDefined();
  expect(minxp.errors['required']).toBeDefined();

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

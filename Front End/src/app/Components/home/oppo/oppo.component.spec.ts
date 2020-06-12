import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { OppoComponent } from './oppo.component';
import { RouterTestingModule } from '@angular/router/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule, MAT_DIALOG_DATA,MatDialog, MatDialogRef } from '@angular/material/dialog';
import { OppoService } from 'src/app/Services/home.service/oppo.service';
import { of } from 'rxjs';
import { By } from '@angular/platform-browser'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserService } from 'src/app/Services/home.service/user.service';
import { Mock } from 'protractor/built/driverProviders';

// mock object with close method
export class dialogMock {
  close() {

  }
  open() {

  }
};


describe('OppoComponent', () => {
  let component: OppoComponent;
  let fixture: ComponentFixture < OppoComponent > ;
  let oppoService: OppoService;
  let userService: UserService;
  let adialog: dialogMock;


  beforeEach(async(() => {
      TestBed.configureTestingModule({
              imports: [ReactiveFormsModule, BrowserAnimationsModule, HttpClientModule, RouterTestingModule, MatSnackBarModule, MatDialogModule],
              declarations: [OppoComponent],
              providers: [OppoService, UserService,
                  {
                      provide: MatDialogRef,
                      useValue: dialogMock
                  },
                  {
                      provide: MAT_DIALOG_DATA,
                      useValue: {}
                  }

              ]
          })
          .compileComponents();
  }));

  beforeEach(() => {
      fixture = TestBed.createComponent(OppoComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
      oppoService = TestBed.get(OppoService);
      userService = TestBed.get(UserService);
      // dialog = TestBed.get(MatDialog);
  });


  it('dialog box open', () => {
      let btn = fixture.debugElement.query(By.css('#CreateOpportunity'));
  });

  it('Should Create Opportunity Component', () => {
      expect(component).toBeTruthy();
  });

  it('Should Call Delete Method', () => {
      spyOn(oppoService, 'del').and.returnValue( of (1));
      spyOn(oppoService, 'getAllOpp').and.returnValue( of ([name]));
      component.delRecord(1);
      expect(oppoService.del).toHaveBeenCalled();
      expect(oppoService.getAllOpp).toHaveBeenCalled();
      expect(component.dataSource.data).toEqual([name]);
  });


  it('Should Call GetAll Method and Return No Data', () => {
      spyOn(oppoService, 'getAllOpp').and.returnValue( of ([]));
      component.view();
      expect(oppoService.getAllOpp).toHaveBeenCalled();
  });

  it('Should Check Form InValid without Data', () => {
      expect(component.myform.valid).toBeFalsy();
  });

  it('Should Check Form Valid with Data', () => {
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

  it('Should Check Form Displaying Errors when No Data', () => {
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

  it('Should Call Edit Method', () => {
      spyOn(oppoService, 'editOpp').and.returnValue( of ([]));
      spyOn(oppoService, 'getAllOpp').and.returnValue( of ([]));
      let data: any[] = [{
          endDate: '2020-04-05'
      }];
      component.onEdit(data);
      expect(oppoService.editOpp).toHaveBeenCalled();
      expect(oppoService.getAllOpp).toHaveBeenCalled();
  });

  it('Should Call onInit', () => {
      spyOn(userService, 'getCurrentUser').and.returnValue( of ([]));
      spyOn(userService, 'getAllUser').and.returnValue( of ([]));
      spyOn(oppoService, 'getAllOpp').and.returnValue( of ([]));
      let data: any[] = [{
          endDate: '2020-04-05'
      }];
      component.ngOnInit();
      expect(userService.getCurrentUser).toHaveBeenCalled();
      expect(userService.getAllUser).toHaveBeenCalled();
      expect(oppoService.getAllOpp).toHaveBeenCalled();
  });

  it('Should Call No Function of Del Dialog', () => {
      spyOn(component.snackBar, 'open').and.callThrough();
      component.no();
      expect(component.snackBar.open).toHaveBeenCalled();
  });

  it('Should Call Yes Function of Del Dialog', () => {
      spyOn(component, 'delRecord').and.returnValue( of ());
      component.yes();
      expect(component.delRecord).toHaveBeenCalled();
  });

  it('Should Check "Open" Add Dialog Box', () => {
      spyOn(component.dialog, 'open').and.callThrough();
      component.openAddDialog();
      let formvalue = component.myform.value;
      expect(formvalue.demand).toEqual(component.defaultOpportunity.demand);
      expect(component.dialog.open).toHaveBeenCalled();
  });


  it('Should Check "Open" Del Dialog Box', () => {
      spyOn(component.dialog, 'open').and.callThrough();
      component.opendelDialog([{
          name
      }]);
      expect(component.oppodata).toEqual([{
          name
      }]);
      expect(component.dialog.open).toHaveBeenCalled();
  });

  it('Should Check "Open" Edit Dialog Box', () => {
      spyOn(component.dialog, 'open').and.callThrough();
      component.opendeditDialog([{}]);
      expect(component.dialog.open).toHaveBeenCalled();
  });

  it('Should Call Submit', () => {
      // component.addDialog = this.adialog.open();
      spyOn(oppoService, 'addOpp').and.returnValue( of ([]));
      spyOn(oppoService, 'getAllOpp').and.returnValue( of ([]));
      // spyOn(component.addDialog,'close').and.callThrough();
      component.onSubmit([{}]);
      expect(oppoService.addOpp).toHaveBeenCalled();
  });

  it('Should Test On Cancel Method With Invalid Input', () => {
      spyOn(component.snackBar, 'open').and.callThrough();
      component.onCancel('');
      expect(component.snackBar.open).toHaveBeenCalled();
  });
});

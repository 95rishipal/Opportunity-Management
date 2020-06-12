import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { AuditComponent } from './audit.component';
import { MatDialogModule } from '@angular/material/dialog';
import { AuditService } from '../../../Services/home.service/audit.service';
import { of, throwError } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from "@angular/material/snack-bar";

export class MaterialTableComponent {
  displayedColumns: string[] = ['position', 'name'];
  dataSource = [{
      position: 1,
      name: 'Hydrogen'
  }];
}

describe('AuditComponent', () => {
  let component: AuditComponent;
  let fixture: ComponentFixture < AuditComponent > ;
  let auditService: AuditService;
  beforeEach(async(() => {
      TestBed.configureTestingModule({
              imports: [HttpClientModule, MatDialogModule, MatSnackBarModule, BrowserAnimationsModule],
              declarations: [AuditComponent],
              providers: [AuditService]
          })
          .compileComponents();
  }));

  beforeEach(() => {
      fixture = TestBed.createComponent(AuditComponent);
      auditService = TestBed.get(AuditService);
      component = fixture.componentInstance;
      fixture.detectChanges();
  });

  it('Should Create Audit Component', () => {
      expect(component).toBeTruthy();
  });

  it('Should Call GetAll Method and Return NO Data', () => {
      spyOn(auditService, 'getall').and.returnValue( of ([]));
      let data = component.getAll();
      expect(auditService.getall).toHaveBeenCalled();
      expect(component.dataSource.data).toEqual([]);
  });

  it('Should Call GetAll Method and Return Some Data', () => {
      spyOn(auditService, 'getall').and.returnValue( of ([name]));
      let data = component.getAll();
      expect(auditService.getall).toHaveBeenCalled();
      expect(component.dataSource.data).toEqual([name]);
  });

  it('Should Call GetAll Method and Throw Error', () => {
      spyOn(auditService, 'getall').and.returnValue(throwError("No Service"));
      let data = component.getAll();
      expect(auditService.getall).toHaveBeenCalled();
  });

  it('Should Call Search Method and Return No Data', () => {
      spyOn(auditService, 'search').and.returnValue( of ([]));
      component.searchM();
      expect(auditService.search).toHaveBeenCalled();
  });

  it('Should Call Search Fuction And Return Some Data', () => {
      spyOn(auditService, 'search').and.returnValue( of ([name]));
      component.searchM();
      expect(auditService.search).toHaveBeenCalled();
      expect(component.dataSource.data).toEqual([name]);
  });

  it('Should Call Search Fuction And Throw Error', () => {
      spyOn(auditService, 'search').and.returnValue(throwError("Test Error"));
      component.searchM();
      expect(auditService.search).toHaveBeenCalled();
  });


  it('Should Call the View Fuction and Check Data Bind or Not', () => {
      component.view([]);
      expect(component.data).toEqual([]);
  });
});



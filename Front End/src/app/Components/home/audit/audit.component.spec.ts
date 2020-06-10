import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { AuditComponent } from './audit.component';
import { MatDialogModule } from '@angular/material/dialog';
import { AuditService } from '../../../Services/home.service/audit.service';
import { of } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from "@angular/material/snack-bar";


export class MaterialTableComponent {
  displayedColumns: string[] = ['position', 'name'];
  dataSource = [{position: 1, name: 'Hydrogen'}];
}

describe('AuditComponent', () => {
  let component: AuditComponent;
  let fixture: ComponentFixture<AuditComponent>;
  let auditService: AuditService;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule, MatDialogModule, MatSnackBarModule, BrowserAnimationsModule],
      declarations: [ AuditComponent ],
      providers:[AuditService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuditComponent);
    auditService = TestBed.get(AuditService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should working get all Call', () => {
      spyOn(auditService,'getall').and.returnValue(of([]));
      let data = component.getAll();
      expect(auditService.getall).toHaveBeenCalled();
  });

  it('should working search Call', () => {
    spyOn(auditService,'search').and.returnValue(of([]));
    component.searchM();
    expect(auditService.search).toHaveBeenCalled();
});

});



import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OppoComponent } from './oppo.component';

describe('OppoComponent', () => {
  let component: OppoComponent;
  let fixture: ComponentFixture<OppoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OppoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OppoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

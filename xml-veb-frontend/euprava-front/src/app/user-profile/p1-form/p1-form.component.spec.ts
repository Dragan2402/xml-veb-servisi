import { ComponentFixture, TestBed } from '@angular/core/testing';

import { P1FormComponent } from './p1-form.component';

describe('P1FormComponent', () => {
  let component: P1FormComponent;
  let fixture: ComponentFixture<P1FormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ P1FormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(P1FormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

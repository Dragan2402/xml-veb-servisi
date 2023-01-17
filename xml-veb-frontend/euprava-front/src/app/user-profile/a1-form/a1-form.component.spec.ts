import { ComponentFixture, TestBed } from '@angular/core/testing';

import { A1FormComponent } from './a1-form.component';

describe('A1FormComponent', () => {
  let component: A1FormComponent;
  let fixture: ComponentFixture<A1FormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ A1FormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(A1FormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Z1FormComponent } from './z1-form.component';

describe('P1FormComponent', () => {
  let component: Z1FormComponent;
  let fixture: ComponentFixture<Z1FormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Z1FormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Z1FormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { A1RequestComponent } from './a1-request.component';

describe('A1RequestComponent', () => {
  let component: A1RequestComponent;
  let fixture: ComponentFixture<A1RequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ A1RequestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(A1RequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

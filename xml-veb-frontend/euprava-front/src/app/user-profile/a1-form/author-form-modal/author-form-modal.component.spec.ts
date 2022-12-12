import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthorFormModalComponent } from './author-form-modal.component';

describe('AuthorFormModalComponent', () => {
  let component: AuthorFormModalComponent;
  let fixture: ComponentFixture<AuthorFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthorFormModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuthorFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

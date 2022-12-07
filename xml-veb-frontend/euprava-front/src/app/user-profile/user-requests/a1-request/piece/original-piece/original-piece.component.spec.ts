import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OriginalPieceComponent } from './original-piece.component';

describe('OriginalPieceComponent', () => {
  let component: OriginalPieceComponent;
  let fixture: ComponentFixture<OriginalPieceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OriginalPieceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OriginalPieceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

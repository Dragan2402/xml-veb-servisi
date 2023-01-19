import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MetadataDownloadComponent } from './metadata-download.component';

describe('MetadataDownloadComponent', () => {
  let component: MetadataDownloadComponent;
  let fixture: ComponentFixture<MetadataDownloadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MetadataDownloadComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MetadataDownloadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

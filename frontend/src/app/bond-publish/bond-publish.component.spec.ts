import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BondPublishComponent } from './bond-publish.component';

describe('BondPublishComponent', () => {
  let component: BondPublishComponent;
  let fixture: ComponentFixture<BondPublishComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BondPublishComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BondPublishComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

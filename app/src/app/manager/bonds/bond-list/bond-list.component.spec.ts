import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BondListComponent } from './bond-list.component';

describe('BondListComponent', () => {
  let component: BondListComponent;
  let fixture: ComponentFixture<BondListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BondListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BondListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

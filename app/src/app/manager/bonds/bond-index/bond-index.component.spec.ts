import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BondIndexComponent } from './bond-index.component';

describe('BondIndexComponent', () => {
  let component: BondIndexComponent;
  let fixture: ComponentFixture<BondIndexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BondIndexComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BondIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

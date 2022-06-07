import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssuerDetailComponent } from './issuer-detail.component';

describe('IssuerDetailComponent', () => {
  let component: IssuerDetailComponent;
  let fixture: ComponentFixture<IssuerDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IssuerDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IssuerDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

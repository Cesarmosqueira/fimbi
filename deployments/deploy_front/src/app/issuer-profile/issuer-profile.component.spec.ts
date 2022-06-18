import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssuerProfileComponent } from './issuer-profile.component';

describe('IssuerProfileComponent', () => {
  let component: IssuerProfileComponent;
  let fixture: ComponentFixture<IssuerProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IssuerProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IssuerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

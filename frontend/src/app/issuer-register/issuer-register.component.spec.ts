import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssuerRegisterComponent } from './issuer-register.component';

describe('IssuerRegisterComponent', () => {
  let component: IssuerRegisterComponent;
  let fixture: ComponentFixture<IssuerRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IssuerRegisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IssuerRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LiborChartComponent } from './libor-chart.component';

describe('LiborChartComponent', () => {
  let component: LiborChartComponent;
  let fixture: ComponentFixture<LiborChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LiborChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LiborChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

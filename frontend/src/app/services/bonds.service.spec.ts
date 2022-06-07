import { TestBed } from '@angular/core/testing';

import { BondsService } from './bonds.service';

describe('BondsService', () => {
  let service: BondsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BondsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

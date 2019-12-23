import { TestBed } from '@angular/core/testing';

import { NcService } from './nc.service';

describe('NcService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NcService = TestBed.get(NcService);
    expect(service).toBeTruthy();
  });
});

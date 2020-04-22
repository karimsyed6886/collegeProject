import { TestBed } from '@angular/core/testing';

import { SampleRoutegardService } from './sample-routegard.service';

describe('SampleRoutegardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SampleRoutegardService = TestBed.get(SampleRoutegardService);
    expect(service).toBeTruthy();
  });
});

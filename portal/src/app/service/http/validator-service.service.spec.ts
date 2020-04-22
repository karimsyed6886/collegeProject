import { TestBed } from '@angular/core/testing';

import { ValidatorServiceService } from './validator-service.service';

describe('ValidatorServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ValidatorServiceService = TestBed.get(ValidatorServiceService);
    expect(service).toBeTruthy();
  });
});

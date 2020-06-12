import { TestBed } from '@angular/core/testing';

import { LocalstorageService } from './localstorage.service.service';

describe('Localstorage.ServiceService', () => {
  let service: LocalstorageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LocalstorageService);
  });

  it('Should Create Local Storage Sercive', () => {
    expect(service).toBeTruthy();
  });

  it('Should Check Auth Data', () => {
    service.setAuthData("1234");
    expect(localStorage.getItem("Token")).toEqual("1234");
  }); 


  it('Should Check  Google ID Data', () => {
    service.setGid("1234");
    expect(localStorage.getItem("Gid")).toEqual("1234");
  }); 

  it('Should Check User Email Data', () => {
    service.setUserData("1234");
    expect(localStorage.getItem("Email")).toEqual("1234");
  }); 

});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginActivityComponent } from './login-activity.component';

describe('LoginActivityComponent', () => {
  let component: LoginActivityComponent;
  let fixture: ComponentFixture<LoginActivityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginActivityComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoginActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

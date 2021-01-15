import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FirstGuiComponent } from './first-gui.component';

describe('FirstGuiComponent', () => {
  let component: FirstGuiComponent;
  let fixture: ComponentFixture<FirstGuiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FirstGuiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FirstGuiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

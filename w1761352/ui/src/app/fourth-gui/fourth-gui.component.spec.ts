import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FourthGuiComponent } from './fourth-gui.component';

describe('FourthGuiComponent', () => {
  let component: FourthGuiComponent;
  let fixture: ComponentFixture<FourthGuiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FourthGuiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FourthGuiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ThirdGuiComponent } from './third-gui.component';

describe('ThirdGuiComponent', () => {
  let component: ThirdGuiComponent;
  let fixture: ComponentFixture<ThirdGuiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ThirdGuiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ThirdGuiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

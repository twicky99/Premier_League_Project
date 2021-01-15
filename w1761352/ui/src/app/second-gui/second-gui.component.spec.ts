import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SecondGuiComponent } from './second-gui.component';

describe('SecondGuiComponent', () => {
  let component: SecondGuiComponent;
  let fixture: ComponentFixture<SecondGuiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SecondGuiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SecondGuiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

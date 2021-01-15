import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SecondGuiModalComponent } from './second-gui-modal.component';

describe('SecondGuiModalComponent', () => {
  let component: SecondGuiModalComponent;
  let fixture: ComponentFixture<SecondGuiModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SecondGuiModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SecondGuiModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

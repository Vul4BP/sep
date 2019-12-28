import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormabankeComponent } from './formabanke.component';

describe('FormabankeComponent', () => {
  let component: FormabankeComponent;
  let fixture: ComponentFixture<FormabankeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormabankeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormabankeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

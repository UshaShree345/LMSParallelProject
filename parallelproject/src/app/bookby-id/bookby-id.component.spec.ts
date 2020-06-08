import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookbyIdComponent } from './bookby-id.component';

describe('BookbyIdComponent', () => {
  let component: BookbyIdComponent;
  let fixture: ComponentFixture<BookbyIdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookbyIdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookbyIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

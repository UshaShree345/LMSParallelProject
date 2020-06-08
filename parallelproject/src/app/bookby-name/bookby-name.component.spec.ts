import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookbyNameComponent } from './bookby-name.component';

describe('BookbyNameComponent', () => {
  let component: BookbyNameComponent;
  let fixture: ComponentFixture<BookbyNameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookbyNameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookbyNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

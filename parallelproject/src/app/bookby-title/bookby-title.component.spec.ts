import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookbyTitleComponent } from './bookby-title.component';

describe('BookbyTitleComponent', () => {
  let component: BookbyTitleComponent;
  let fixture: ComponentFixture<BookbyTitleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookbyTitleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookbyTitleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

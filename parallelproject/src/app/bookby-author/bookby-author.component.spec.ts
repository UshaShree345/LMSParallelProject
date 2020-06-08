import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookbyAuthorComponent } from './bookby-author.component';

describe('BookbyAuthorComponent', () => {
  let component: BookbyAuthorComponent;
  let fixture: ComponentFixture<BookbyAuthorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookbyAuthorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookbyAuthorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

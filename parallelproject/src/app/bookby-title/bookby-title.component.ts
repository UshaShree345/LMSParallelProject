import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bookby-title',
  templateUrl: './bookby-title.component.html',
  styleUrls: ['./bookby-title.component.css']
})
export class BookbyTitleComponent implements OnInit {

  books: string;
  search;
  book;
  bookbean;
  constructor(private bookService: BookserviceService, private router: Router) {
    this.getBooksByTitle(this.book);
  }

  ngOnInit(): void {
  }

  getBooksByTitle(bookbean) {
    this.bookService.getSearchByTitle(bookbean).subscribe(response => {
      console.log(response);
      this.books = response.books;
    }, error => {
      console.log(error);
    }
    );
  }

}

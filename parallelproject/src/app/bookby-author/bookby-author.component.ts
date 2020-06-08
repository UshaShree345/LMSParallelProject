import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bookby-author',
  templateUrl: './bookby-author.component.html',
  styleUrls: ['./bookby-author.component.css']
})
export class BookbyAuthorComponent implements OnInit {
  bId: number;
  bookName: string;
  author: string;
  category: string;
  publisher: string;
  search;
  booksInfo;
  books: string[];
  bookbean: string;
  book;
  constructor(private bookService: BookserviceService, private router: Router) {
    this.getBooks();
  }

  
  ngOnInit(): void {
  }

  getBooks() {
    this.bookService.getData().subscribe(response => {
      console.log(response);
      this.booksInfo = response.booksInfo;
    }, error => {
      console.log(error);
    }
    );
  }

}

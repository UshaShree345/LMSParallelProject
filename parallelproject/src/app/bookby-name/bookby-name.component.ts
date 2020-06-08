import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bookby-name',
  templateUrl: './bookby-name.component.html',
  styleUrls: ['./bookby-name.component.css']
})
export class BookbyNameComponent implements OnInit {
  book;
  search;
  books;
  constructor(private bookService: BookserviceService, private router: Router) {
    this.getBooksByTitle();
  }

  ngOnInit(): void {
  }

  getBooksByTitle() {
    this.bookService.getData().subscribe(response => {
      console.log(response);
      this.book = response.book;
    }, error => {
      console.log(error);
    }
    );
  }

}
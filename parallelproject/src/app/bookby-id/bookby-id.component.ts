import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bookby-id',
  templateUrl: './bookby-id.component.html',
  styleUrls: ['./bookby-id.component.css']
})
export class BookbyIdComponent implements OnInit {

  bookbean: number;
  constructor(private bookService: BookserviceService, private router: Router) {
    this.getBooksById(this.bookbean);
  }

  books;
  ngOnInit(): void {
  }

  getBooksById(bookbean) {
    this.bookService.getSearchById(bookbean).subscribe(response => {
      console.log(response);
      this.books = response.books;
    }, error => {
      console.log(error);
    }
    );
  }

}

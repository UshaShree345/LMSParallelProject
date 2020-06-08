import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookserviceService } from '../bookservice.service';

@Component({
  selector: 'app-return-book',
  templateUrl: './return-book.component.html',
  styleUrls: ['./return-book.component.css']
})
export class ReturnBookComponent implements OnInit {

  message: string;
  borrowed;
  error;
  constructor(private bookService: BookserviceService,
              private router: Router) {
    this.getBooks();
  }
  ngOnInit(): void {
  }
  getBooks() {
    const userDetails = JSON.parse(localStorage.getItem('user'));
    this.bookService.getBorrowedData({ id: userDetails.user.id }).subscribe(response => {
      console.log(response);
      alert('Books Borrowed are below');
      this.borrowed = response.borrowed;
    }, error => {
      console.log(error);
    });
  }
  bookReturn(book) {
    this.bookService.returnData({ bookId: book.bookId, uId: book.uId }).subscribe(response => {
      if (response.error) {
        this.error = 'Book is not Returned';
        setTimeout(() => {
          this.error = null;
        }, 5000);
      } else {
        this.message = 'Book returned to the Library';
      }
      setTimeout(() => {
        this.error = null;
      }, 5000);
    });
  }

}

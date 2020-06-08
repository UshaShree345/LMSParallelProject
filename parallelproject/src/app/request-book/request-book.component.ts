import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { BookserviceService } from '../bookservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-request-book',
  templateUrl: './request-book.component.html',
  styleUrls: ['./request-book.component.css']
})
export class RequestBookComponent implements OnInit {

  message: string;
  book;
  error;
  search;

  constructor(private bookService: BookserviceService) {
    this.getBooks();
  }
  ngOnInit(): void {
  }
  getBooks() {
    this.bookService.getData().subscribe(response => {
      console.log(response);
      this.book = response.book;
    }, error => {
      console.log(error);
    }
    );
  }
  userRequest(bookId) {
    const userDetails = JSON.parse(localStorage.getItem('user'));
    const userId = userDetails.user.id;
    this.bookService.requestData({ id: userId, bookId }).subscribe(response => {
      console.log(response);
      if (response.error) {
        this.error = response.message;
        alert('You have already requested the same book');
        setTimeout(() => {
          this.error = null;
        }, 5000);
      } else {
        alert('request placed successfully');
        this.message = 'Request Placed to admin';
        setTimeout(() => {
          this.message = null;
        }, 5000);
      }
    }
    );
  }
}

import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-requests',
  templateUrl: './show-requests.component.html',
  styleUrls: ['./show-requests.component.css']
})
export class ShowRequestsComponent implements OnInit {

  details;
  message: string;
  error;
  constructor(private bookService: BookserviceService,
              private router: Router) {
    this.getRequestDetails();
  }

  ngOnInit(): void {
  }

  getRequestDetails() {
    this.bookService.getRequestData().subscribe(response => {
      console.log(response);
      this.details = response.details;
    }, error => {
      console.log(error);
    }
    );
  }

  bookIssue(detail) {
    this.bookService.issueData({ uId: detail.uId, bookId: detail.bookId }).subscribe(response => {
      console.log(response);
      if (!response.error) {
        alert('Book Issued to the User');
        this.message = 'Book Issued to the User';
      } else {
        this.error = 'Book  is not Issued';
      }
      this.getRequestDetails();
      setTimeout(() => {
        this.message = null;
      }, 5000);
    });
  }
}

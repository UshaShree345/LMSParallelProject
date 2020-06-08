import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-issued-books',
  templateUrl: './issued-books.component.html',
  styleUrls: ['./issued-books.component.css']
})
export class IssuedBooksComponent implements OnInit {

  issue;
  constructor(private bookService: BookserviceService, private router: Router) {
    this.getIssuedDetails();
  }

  ngOnInit(): void {
  }

  getIssuedDetails() {
    this.bookService.getIssuedData().subscribe(response => {
      console.log(response);
      this.issue = response.issue;
    }, error => {
      console.log(error);
    }
    );
  }
}

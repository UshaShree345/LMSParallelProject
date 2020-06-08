import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {

  bookToBeUpdated;
  message: string;
  error: string;
  constructor(private route: ActivatedRoute,
              private bookService: BookserviceService,
              private router: Router
  ) {
    this.route.queryParams.subscribe(data => {
      console.log('Book to be updated', data);
      this.bookToBeUpdated = data;
    });
  }
  ngOnInit(): void {
  }

  editBook(form: NgForm) {

    this.bookService.updateData(form.value).subscribe(
      response => {
        console.log('edit form value', form.value);
        console.log(response);
        form.reset();
        if (!response.error) {
          this.error = response.message;
          alert('successfully Updated');
          this.router.navigateByUrl('/booksInfo');
        } else {
          alert('Failed to update');
          this.router.navigateByUrl('/booksInfo');
        }
      }
    );
  }


}


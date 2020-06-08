import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { BookserviceService } from '../bookservice.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  message: string;
  constructor(private bookService: BookserviceService) { }

  ngOnInit(): void {
  }

  postBook(form: NgForm){
    this.bookService.postData(form.value).subscribe(response => {
      console.log(response);
      form.reset();
      if (!response.error){
        this.message = 'Book added successfully';
      }
    }, error => {
      console.log(error);
    });
   }

}

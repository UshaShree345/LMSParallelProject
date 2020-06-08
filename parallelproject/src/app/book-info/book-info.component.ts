import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-info',
  templateUrl: './book-info.component.html',
  styleUrls: ['./book-info.component.css']
})
export class BookInfoComponent implements OnInit {

search;
message: string;
  book: any; 
  constructor(private bookService : BookserviceService,private router :Router) { 
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
  deleteBook(bookId: number) {
    
    this.bookService.deleteData(bookId).subscribe(response => {
      console.log(response);
      if (response) {
        
        // tslint:disable-next-line: quotemark
        alert("Book Deleted SuccessFully");
        // alert("deleted successfully");
       // this.router.navigateByUrl('/booksInfo');
      } else {
        alert('Book not deleted');
       // this.router.navigateByUrl('/booksInfo');
      }
      this.getBooks();
      setTimeout(() =>
      {
        this.message = null;
      }, 5000);
    }
    );
    this.router.navigateByUrl('/booksInfo');
  }

  selectBook(books){
    this.router.navigate(['/updateBook'], {queryParams: books});
  }
  }
  
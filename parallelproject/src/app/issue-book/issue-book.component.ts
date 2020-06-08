import { Component, OnInit } from '@angular/core';
import { BookserviceService } from '../bookservice.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-issue-book',
  templateUrl: './issue-book.component.html',
  styleUrls: ['./issue-book.component.css']
})
export class IssueBookComponent implements OnInit {
  message;
  details;
  constructor( private bookService:BookserviceService) { }

  ngOnInit(): void {
  }

  bookIssue(form: NgForm){
    this.bookService.issueData(form.value).subscribe(response =>{
      console.log(response);
      if(!response.error){
        //this.message = 'Book Successfully Issued to the Users';
        alert("Book Issued");
      }else {
        this.message = 'Book is not Issued';
      }
      setTimeout(()=>{
        this.message = null;
      },5000);
    });
  }
}
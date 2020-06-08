import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { AdminComponent } from './admin/admin.component';
import { SearchComponent } from './search/search.component';
import { StudentComponent } from './student/student.component';
import { HomeComponent } from './home/home.component';
import { AddBookComponent } from './add-book/add-book.component';
import { RemoveBookComponent } from './remove-book/remove-book.component';
import { UpdateBookComponent } from './update-book/update-book.component';
import { IssueBookComponent } from './issue-book/issue-book.component';
import { ShowUsersComponent } from './show-users/show-users.component';
import { ShowRequestsComponent } from './show-requests/show-requests.component';
import { IssuedBooksComponent } from './issued-books/issued-books.component';
import { RequestBookComponent } from './request-book/request-book.component';
import { ReturnBookComponent } from './return-book/return-book.component';
import { BorrowedBooksComponent } from './borrowed-books/borrowed-books.component';
import { BookbyIdComponent } from './bookby-id/bookby-id.component';
import { BookbyTitleComponent } from './bookby-title/bookby-title.component';
import { BookbyAuthorComponent } from './bookby-author/bookby-author.component';
import { BookInfoComponent } from './book-info/book-info.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FilterPipe } from './filter.pipe';
import { FilterAuthorPipe } from './filter-author.pipe';
import { AboutComponent } from './about/about.component';
import { ContactusComponent } from './contactus/contactus.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { BookbyNameComponent } from './bookby-name/bookby-name.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AdminComponent,
    SearchComponent,
    StudentComponent,
    HomeComponent,
    AddBookComponent,
    RemoveBookComponent,
    UpdateBookComponent,
    IssueBookComponent,
    ShowUsersComponent,
    ShowRequestsComponent,
    IssuedBooksComponent,
    RequestBookComponent,
    ReturnBookComponent,
    BorrowedBooksComponent,
    BookbyIdComponent,
    BookbyTitleComponent,
    BookbyAuthorComponent,
    BookInfoComponent,
    LoginComponent,
    RegisterComponent,
    FilterPipe,
    FilterAuthorPipe,
    AboutComponent,
    ContactusComponent,
    WelcomeComponent,
    BookbyNameComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

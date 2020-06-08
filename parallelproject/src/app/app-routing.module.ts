import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { StudentComponent } from './student/student.component';
import { SearchComponent } from './search/search.component';
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
import { AuthGuard } from './authguard';
import { AboutComponent } from './about/about.component';
import { ContactusComponent } from './contactus/contactus.component';
import { BookbyNameComponent } from './bookby-name/bookby-name.component';


const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'admin', component: AdminComponent, data: {roles: ['admin']}, canActivate: [AuthGuard]},
  {path: 'student', component: StudentComponent, data: {roles: ['student']}, canActivate: [AuthGuard]},
  {path: 'search', component: SearchComponent, data: {roles: ['admin', 'student']}, canActivate: [AuthGuard]},
  {path: 'addBook', component: AddBookComponent, data: {roles: ['admin']}, canActivate: [AuthGuard]},
  {path: 'removeBook', component: RemoveBookComponent, data: {roles: ['admin']}, canActivate: [AuthGuard]},
  {path: 'updateBook', component: UpdateBookComponent, data: {roles: ['admin']}, canActivate: [AuthGuard]},
  {path: 'issueBook', component: IssueBookComponent,  data: {roles: ['admin']}},
  {path: 'showUsers', component: ShowUsersComponent,  data: {roles: ['admin']}},
  {path: 'showRequests', component: ShowRequestsComponent,  data: {roles: ['admin']}},
  {path: 'issuedBooks', component: IssuedBooksComponent,  data: {roles: ['admin']}},
  {path: 'requestBook', component: RequestBookComponent, data: {roles: ['student']}},
  {path: 'returnBook', component: ReturnBookComponent, data: {roles: ['student']}},
  {path: 'borrowedBooks', component: BorrowedBooksComponent, data: {roles: ['student']}},
  {path: 'bookById', component: BookbyIdComponent},
  {path: 'bookByTitle', component: BookbyTitleComponent},
  {path: 'bookByAuthor', component: BookbyAuthorComponent},
  {path: 'booksInfo', component: BookInfoComponent, data: {roles: ['admin', 'student']}, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'about', component: AboutComponent},
  {path: 'contactus', component: ContactusComponent},
  {path: 'bookby-name', component: BookbyNameComponent,  data: {roles: ['admin', 'student']}, canActivate: [AuthGuard]},
  {path: '', redirectTo: 'home', pathMatch: 'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

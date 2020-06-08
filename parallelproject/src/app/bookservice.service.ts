import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BookserviceService {

  bookId;
  constructor(private http: HttpClient) { }
  myURL = 'http://localhost:8080/lmsspringrest/';

  getData() {
    return this.http.get<any>(`${this.myURL}booksInfo`);
  }
  postData(books) {
    return this.http.post<any>(`${this.myURL}addBook`, books);
  }
  deleteData(bookId) {
    return this.http.delete<any>(`${this.myURL}removeBook/` + bookId);
  }
  updateData(books) {
    return this.http.put<any>(`${this.myURL}updateBook`, books);
  }
  getRequestData() {
    return this.http.get<any>(`${this.myURL}showRequests`);
  }

  getUserData() {
    return this.http.get<any>(`${this.myURL}showUsers`);
  }

  getIssuedData() {
    return this.http.get<any>(`${this.myURL}showIssuedBooks`);
  }

  getSearchById(bookDetails) {
    return this.http.get<any>(`${this.myURL}booksById/${bookDetails}`);
  }

  getSearchByTitle(bookDetails) {
    return this.http.get<any>(`${this.myURL}booksByName/${bookDetails}`);
  }

  getSearchByAuthor(bookDetails) {
    return this.http.get<any>(`${this.myURL}booksByAuthor/${bookDetails}`);
  }

  getBorrowedData(bean) {
    return this.http.post<any>(`${this.myURL}borrowedBooks`, bean);
  }

  issueData(books) {
    return this.http.post<any>(`${this.myURL}bookIssue`, books);
  }

  requestData(books) {
    return this.http.post<any>(`${this.myURL}requestBook`, books);
  }

  returnData(books) {
    return this.http.post<any>(`${this.myURL}returnBook`, books);
  }
}




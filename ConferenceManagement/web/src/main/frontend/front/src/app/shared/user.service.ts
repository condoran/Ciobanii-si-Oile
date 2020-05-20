// @ts-ignore
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from './user.model';
import {Observable} from 'rxjs';
import {Conference} from "./conference.model";


@Injectable()
export class UserService {
  private userUrl = 'http://localhost:8080/user';

  constructor(private httpClient: HttpClient) {
  }

  login(username: string, password: string): Observable<User>{
    return this.httpClient
      .post<User>(this.userUrl + '/checkUser', [username, password]);
  }

  getConferencesForPCMember(username: string):Observable<Conference[]>{
    return this.httpClient
      .post<Conference[]>(this.userUrl + '/getConferencesForPCMember', username);
  }
  // getBooks(): Observable<Book[]> {
  //   return this.httpClient
  //     .get<Array<Book>>(this.booksUrl);
  // }
  //
  // getBook(id: number): Observable<Book> {
  //   return this.getBooks()
  //     .pipe(
  //       map(books => books.find(book => book.id === id))
  //     );
  // }
  //
  // saveBook(book: Book): Observable<Book> {
  //   console.log("saveBook", book);
  //
  //   return this.httpClient
  //     .post<Book>(this.booksUrl, book);
  // }
  //
  // update(book): Observable<Book> {
  //   const url = `${this.booksUrl}/${book.id}`;
  //   return this.httpClient
  //     .put<Book>(url, book);
  // }
  //
  // deleteBook(id: number): Observable<any> {
  //   const url = `${this.booksUrl}/${id}`;
  //   return this.httpClient
  //     .delete(url);
  // }

}
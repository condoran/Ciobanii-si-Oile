// @ts-ignore
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from './user.model';
import {Observable} from 'rxjs';


@Injectable()
export class UserService {
  private userUrl = 'http://localhost:8080/user';

  constructor(private httpClient: HttpClient) {
  }
  login(username: string, password: string): Observable<Array<string>>{
    const list: Array<string> = [username, password];
    return this.httpClient.post<Array<string>>(this.userUrl + '/checkUser' , list);
  }
  // @ts-ignore
  // @ts-ignore
  login2(username: string, password: string): Observable<User>{
    const body = {username: 'ion', password: '1234'};
    const list: Array<string> = [username, password];
    // return this.httpClient.post<User>(this.userUrl + '/checkUser2' , list);
    return this.httpClient
      .post<User>(this.userUrl + '/checkUser', {username, password});
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

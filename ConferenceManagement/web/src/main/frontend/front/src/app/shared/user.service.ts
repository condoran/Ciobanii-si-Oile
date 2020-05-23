
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from './user.model';
import {Observable} from 'rxjs';
import {Conference} from './conference.model';


@Injectable()
export class UserService {
  private userUrl = 'http://localhost:8080/user';

  currentUser : User;
  constructor(private httpClient: HttpClient) {
  }

  login(username: string, password: string): Observable<User>{
    return this.httpClient
      .post<User>(this.userUrl + '/checkUser', [username, password]);
  }

  register(name: string, username: string, password: string, email: string): Observable<User>{
    let user: User = new User(null, username, name, email, null, password, null, null, null, null);

    return this.httpClient
      .post<User>(this.userUrl + '/saveUser', user);
  }

  getConferencesForPCMember(username: string): Observable<Conference[]>{
    return this.httpClient
      .post<Conference[]>(this.userUrl + '/getConferencesForPCMember', username);
  }

  getUserByUsername(username: string): Observable<User>{
    return this.httpClient.post<User>(this.userUrl + '/getUserByUsername', username);
  }

  setCurrentUser(user: User){
    this.currentUser = user;
  }

}

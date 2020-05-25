
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from './user.model';
import {Observable} from 'rxjs';
import {Conference} from './conference.model';
import {Permission} from "./permission.model";
import {ProposalAuthor} from "./proposalAuthor.model";


@Injectable()
export class UserService {
  private userUrl = 'http://localhost:8080/user';

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

  getUserByEmailAddress(emailAddress: string): Observable<User>{
    return this.httpClient.post<User>(this.userUrl + '/getUserByEmail', emailAddress);
  }

  getPermissionForUserInConference(userID: number, conferenceID: number): Observable<Permission>{
    return this.httpClient.post<Permission>(this.userUrl + '/getPermissionForUserInConference', [userID, conferenceID]);
  }

  getUserCanBeAuthorInProposal(userID: number, proposalID: number): Observable<boolean>{
    return this.httpClient.post<boolean>(this.userUrl + "/getUserCanBeAuthorInProposal", [userID, proposalID]);
  }

  getAllNonSCUsersAndNonPCMembers(conferenceID: number): Observable<User[]>{
    return this.httpClient.post<User[]>(this.userUrl + "/getNonSCMembersAndNonPCMembers", conferenceID);
  }

}

// @ts-ignore
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from './user.model';
import {Observable} from 'rxjs';
import {Conference} from './conference.model';
import {map} from "rxjs/operators";
import {Permission} from "./permission.model";


@Injectable()
export class ConferenceService {
  private conferenceUrl = 'http://localhost:8080/conference';

  constructor(private httpClient: HttpClient) {
  }

  saveConference(conference: Conference): Observable<Conference>{
    console.log(conference);
    return this.httpClient.post<Conference>(this.conferenceUrl + '/saveConference', conference);
  }

  getConferences(): Observable<Conference[]>{
    return this.httpClient
      .get<Conference[]>(this.conferenceUrl + '/getConferences');
  }

  getConference(conferenceID: number): Observable<Conference>{
    return this.getConferences().pipe(map(conferences => conferences.find(conference => conference.id === conferenceID)));
  }

  updateConference(conference: Conference): Observable<Conference>{
    return this.httpClient.post<Conference>(this.conferenceUrl + '/postponeConference', conference);
  }

  setCurrentConference(conferenceID:number):void{
    this.httpClient
      .post<Conference>(this.conferenceUrl + "/getConferenceByID", conferenceID)
      .subscribe(conference => {
        sessionStorage.setItem("conference", JSON.stringify(conference));
      });
  }

  addPermission(permission: Permission) :Observable<Permission>{
    return this.httpClient
      .post<Permission>(this.conferenceUrl + "/saveOrUpdatePermission", permission);
  }
}

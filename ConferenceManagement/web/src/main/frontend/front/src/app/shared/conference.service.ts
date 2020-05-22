// @ts-ignore
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from './user.model';
import {Observable} from 'rxjs';
import {Conference} from './conference.model';
import {map} from "rxjs/operators";


@Injectable()
export class ConferenceService {
  private conferenceUrl = 'http://localhost:8080/conference';

  constructor(private httpClient: HttpClient) {
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

}

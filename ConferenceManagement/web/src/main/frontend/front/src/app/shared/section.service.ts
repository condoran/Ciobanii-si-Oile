
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Section} from "./section.model";
import {Observable} from "rxjs";

@Injectable()
export class SectionService {
  private sectionUrl = 'http://localhost:8080/section';

  constructor(private httpClient: HttpClient) {
  }

  getSectionsForConference(conferenceID: number): Observable<Section[]> {
    return this.httpClient
      .post<Section[]>(this.sectionUrl + "/getSectionsForConference", conferenceID);
  }

}

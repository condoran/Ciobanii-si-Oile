
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Section} from "./section.model";
import {Observable} from "rxjs";
import {User} from "./user.model";

@Injectable()
export class SectionService {
  private sectionUrl = 'http://localhost:8080/section';

  constructor(private httpClient: HttpClient) {
  }

  addSection(section: Section): Observable<Section>{
    console.log(section);
    return this.httpClient.post<Section>(this.sectionUrl + "/saveSection", section);
  }

  getSectionsForConference(conferenceID: number): Observable<Section[]> {
    return this.httpClient
      .post<Section[]>(this.sectionUrl + "/getSectionsForConference", conferenceID);
  }

  getSectionByID(sectionID: number): Observable<Section> {
    return this.httpClient
      .post<Section>(this.sectionUrl + "/getSectionByID", sectionID);
  }

  getCandidatesForSectionChair(conferenceID: number): Observable<User[]>{
    return this.httpClient.post<User[]>(this.sectionUrl + "/getCandidatesForSectionChair", conferenceID);
  }

}

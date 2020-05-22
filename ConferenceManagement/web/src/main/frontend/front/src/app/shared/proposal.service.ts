import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Conference} from "./conference.model";
import {Proposal} from "./proposal.model";

@Injectable()
export class ProposalService{
  private proposalUrl = 'http://localhost:8080/proposal';

  constructor(private httpClient: HttpClient) {
  }

  getProposalsForConference(conferenceID: number): Observable<Proposal[]>{
    return this.httpClient
      .post<Proposal[]>(this.proposalUrl + '/getProposalsForConference', conferenceID);
  }
}

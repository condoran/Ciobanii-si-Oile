import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Conference} from "./conference.model";
import {Proposal} from "./proposal.model";
import {User} from "./user.model";
import {ProposalAuthor} from "./proposalAuthor.model";

@Injectable()
export class ProposalService{
  private proposalUrl = 'http://localhost:8080/proposal';

  constructor(private httpClient: HttpClient) {
  }

  getProposalsForConference(conferenceID: number): Observable<Proposal[]>{
    return this.httpClient
      .post<Proposal[]>(this.proposalUrl + '/getProposalsForConference', conferenceID);
  }

  addProposal(name: string, keywords:string, topics: string, conference:Conference):
    Observable<Proposal>{
    let proposal : Proposal = new Proposal(null, name, keywords, topics, conference);
    return this.httpClient
      .post<Proposal>(this.proposalUrl + "/saveProposal", proposal);
  }

  addAuthor(proposalAuthor: ProposalAuthor) {
    this.httpClient
      .post(this.proposalUrl + "/addAuthorForProposal", proposalAuthor)
      .subscribe(x => console.log(x));
  }
}

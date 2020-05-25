import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Conference} from "./conference.model";
import {Proposal} from "./proposal.model";
import {User} from "./user.model";
import {ProposalAuthor} from "./proposalAuthor.model";
import {map} from "rxjs/operators";
import {Bidding} from "./bidding.model";

@Injectable()
export class ProposalService{
  private proposalUrl = 'http://localhost:8080/proposal';

  constructor(private httpClient: HttpClient) {
  }

  getProposalsForConference(conferenceID: number): Observable<Proposal[]>{
    return this.httpClient
      .post<Proposal[]>(this.proposalUrl + '/getProposalsForConference', conferenceID);
  }

  getProposalForConference(conferenceID: number, proposalID: number){
    return this.getProposalsForConference(conferenceID).pipe(map(proposals => proposals.find(proposal => proposal.id === proposalID)));
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

  updateProposal(proposal: Proposal): Observable<Proposal>{
    return this.httpClient.post<Proposal>(this.proposalUrl + "/updateProposal", proposal);
  }

  getProposalsIDsForUser(userID:number):Observable<number[]>{
    return this.httpClient
      .post<number[]>(this.proposalUrl + "/getProposalsIDsForUser", userID);
  }

  addBidding(bidding: Bidding): Observable<Bidding>{
    return this.httpClient
      .post<Bidding>(this.proposalUrl + "/bidProposal", bidding);
  }

  getUnbiddenProposalIDs(userID: number): Observable<number[]>{
    return this.httpClient.post<number[]>(this.proposalUrl + "/getUnbiddenProposalsIDs", userID);
  }
}

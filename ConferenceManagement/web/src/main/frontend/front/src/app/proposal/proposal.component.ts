import {Component, Input, OnInit} from '@angular/core';
import {Proposal} from "../shared/proposal.model";
import {ActivatedRoute, Params} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {User} from "../shared/user.model";
import {Conference} from "../shared/conference.model";
import {ProposalService} from "../shared/proposal.service";
import {Bidding} from "../shared/bidding.model";
import {Permission} from "../shared/permission.model";
import {Review} from "../shared/review.model";

@Component({
  selector: 'app-proposal',
  templateUrl: './proposal.component.html',
  styleUrls: ['./proposal.component.css']
})
export class ProposalComponent implements OnInit {

  @Input() proposal: Proposal;
  user : User = JSON.parse(sessionStorage.getItem("user"));
  conference : Conference = JSON.parse(sessionStorage.getItem("conference"))
  currentDate = new Date();
  unbiddenIDs: number[] = JSON.parse(sessionStorage.getItem("biddingIDs"));
  permission: Permission = JSON.parse(sessionStorage.getItem("permission"));
  usersToReview: User[] = null;

  constructor(private route:ActivatedRoute,
              private proposalService: ProposalService) { }

  ngOnInit(): void {
    this.route.params.pipe(switchMap((params: Params) =>
      this.proposalService.getProposalForConference(+params['conferenceID'], +params['proposalID'])))
      .subscribe(proposal => {this.proposal = proposal;});
  }

  bidProposal(proposal: Proposal, accepted: boolean): void{
    const bidding: Bidding = new Bidding(null, accepted, this.user, proposal);
    this.proposalService.addBidding(bidding).subscribe();
    const index = this.unbiddenIDs.indexOf(proposal.id);
    if (index > -1) {
      this.unbiddenIDs.splice(index, 1);
    }
    sessionStorage.setItem("biddingIDs", JSON.stringify(this.unbiddenIDs));
  }

  canDoBidding():boolean{
    return this.permission !== null && this.permission.isPCMember === true &&
      this.unbiddenIDs.indexOf(this.proposal.id) !== -1 && this.currentDate < this.conference.biddingDeadline
  }

  showList() {
    this.proposalService.getUsersForReviewingAProposal(this.proposal.id)
      .subscribe(users => {this.usersToReview = users; console.log(users)});
  }

  setAsReviewer(user: User) {
    this.proposalService.
    addReview(new Review(null, user, this.proposal, "", ""))
      .subscribe();
    this.usersToReview.splice(this.usersToReview.indexOf(user), 1);
  }
}

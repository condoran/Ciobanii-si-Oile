import { Component, OnInit } from '@angular/core';
import {ProposalService} from "../shared/proposal.service";
import {Proposal} from "../shared/proposal.model";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../shared/user.model";
import {Permission} from "../shared/permission.model";
import {UserService} from "../shared/user.service";
import {Bidding} from "../shared/bidding.model";

@Component({
  selector: 'app-proposal-list',
  templateUrl: './proposal-list.component.html',
  styleUrls: ['./proposal-list.component.css']
})
export class ProposalListComponent implements OnInit {
  proposals: Proposal[];
  private conferenceID: number;
  user : User = JSON.parse(sessionStorage.getItem("user"));
  writtenProposals :number[] = JSON.parse(sessionStorage.getItem("proposalsIDs"));
  permission: Permission = JSON.parse(sessionStorage.getItem("permission"));
  unbiddenIDs: number[] = JSON.parse(sessionStorage.getItem("biddingIDs"));

  constructor(private proposalService: ProposalService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.conferenceID = +params['conferenceID']
    });
    this.proposalService.getProposalsForConference(this.conferenceID)
      .subscribe(proposals => this.proposals = proposals);
  }

  goToUpdateProposal(id: number): void{
    this.router.navigate(["conference", this.conferenceID, "proposals", id]);
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

}

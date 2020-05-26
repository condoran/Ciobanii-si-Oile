import { Component, OnInit } from '@angular/core';
import {ProposalService} from "../shared/proposal.service";
import {Proposal} from "../shared/proposal.model";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../shared/user.model";
import {Permission} from "../shared/permission.model";
import {UserService} from "../shared/user.service";
import {Bidding} from "../shared/bidding.model";
import {Location} from "@angular/common";

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



  constructor(private proposalService: ProposalService,
              private route: ActivatedRoute,
              private router: Router,
              private location: Location) { }

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



  goToDetails(id: number) {
    this.router.navigate(["conference", this.conferenceID, "proposals", id, "details"]);
  }

  goBack() {
    this.location.back();
  }
}

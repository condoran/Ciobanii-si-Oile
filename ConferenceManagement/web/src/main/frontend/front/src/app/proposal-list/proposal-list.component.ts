import { Component, OnInit } from '@angular/core';
import {ProposalService} from "../shared/proposal.service";
import {Proposal} from "../shared/proposal.model";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../shared/user.model";
import {UserService} from "../shared/user.service";
import {Permission} from "../shared/permission.model";

@Component({
  selector: 'app-proposal-list',
  templateUrl: './proposal-list.component.html',
  styleUrls: ['./proposal-list.component.css']
})
export class ProposalListComponent implements OnInit {
  proposals: Proposal[];
  private conferenceID: number;
  user : User = JSON.parse(sessionStorage.getItem("user"));
  allowedToUpdate: boolean
  writtenProposals :number[] = JSON.parse(sessionStorage.getItem("proposalsIDs"));

  constructor(private proposalService: ProposalService,
              private route: ActivatedRoute,
              private router: Router,
              private userService: UserService) { }

  ngOnInit(): void {
    console.log(this.writtenProposals);
    this.route.params.subscribe(params => {
      this.conferenceID = +params['conferenceID']
    });
    this.proposalService.getProposalsForConference(this.conferenceID)
      .subscribe(proposals => this.proposals = proposals);
    // this.proposalService.getProposalsIDsForUser(this.user.id)
    //   .subscribe(IDs => this.writtenProposals = IDs);
    // this.writtenProposals = JSON.parse(sessionStorage.getItem("proposalsIDs"));
    console.log(this.writtenProposals);
  }

  goToUpdateProposal(id: number): void{
    this.router.navigate(["conference", this.conferenceID, "proposals", id]);
  }

}

import { Component, OnInit } from '@angular/core';
import {ProposalService} from "../shared/proposal.service";
import {Proposal} from "../shared/proposal.model";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-proposal-list',
  templateUrl: './proposal-list.component.html',
  styleUrls: ['./proposal-list.component.css']
})
export class ProposalListComponent implements OnInit {
  proposals: Proposal[];
  private conferenceID: number;
  constructor(private proposalService: ProposalService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.conferenceID = +params['conferenceID']
    });
    this.proposalService.getProposalsForConference(this.conferenceID).subscribe(proposals => this.proposals = proposals);
  }

  goToUpdateProposal(id: number): void{
    this.router.navigate(["conference", this.conferenceID, "proposals", id]);
  }


}

import {Component, Input, OnInit} from '@angular/core';
import {ProposalService} from "../shared/proposal.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Proposal} from "../shared/proposal.model";
import {switchMap} from "rxjs/operators";
import {Location} from "@angular/common";
import {User} from "../shared/user.model";
import {Conference} from "../shared/conference.model";
import {ProposalAuthor} from "../shared/proposalAuthor.model";
import {Permission} from "../shared/permission.model";
import {UserService} from "../shared/user.service";
import {ConferenceService} from "../shared/conference.service";

@Component({
  selector: 'app-proposal-update',
  templateUrl: './proposal-update.component.html',
  styleUrls: ['./proposal-update.component.css']
})
export class ProposalUpdateComponent implements OnInit {

  @Input() proposal: Proposal;
  user : User = JSON.parse(sessionStorage.getItem("user"));
  conference : Conference = JSON.parse(sessionStorage.getItem("conference"));
  wantToAddAuthor :Boolean = false;

  constructor(private proposalService: ProposalService,
              private route: ActivatedRoute,
              private location: Location,
              private userService: UserService,
              private conferenceService: ConferenceService) { }

  ngOnInit(): void {
    this.route.params.pipe(switchMap((params: Params) => this.proposalService.getProposalForConference(+params['conferenceID'], +params['proposalID'])))
      .subscribe(proposal => {this.proposal = proposal;
      console.log(this.proposal)});
  }

  updateProposal(): void {
    this.proposalService.updateProposal(this.proposal).subscribe();
  }

  goBack() {
    this.location.back();
  }

  addAuthor(emailAddress: string) {
    this.wantToAddAuthor = false;
    this.userService.getUserByEmailAddress(emailAddress).subscribe(user => {
      if (user === null || user.isChair === true || user.isCoChair === true || user.isSCMember === true){
        alert("Invalid email address! Author must have an account and mustn't be a Chair/Co-Chair or a Steering Committee Member!");
      }
      else {
        this.proposalService.addAuthor(new ProposalAuthor(null, user, this.proposal));
        this.conferenceService.addPermission(new Permission(null, this.conference,
          user, true, null, null))
          .subscribe();
      }
    });
  }
}

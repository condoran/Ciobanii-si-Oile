import { Component, OnInit } from '@angular/core';
import {ConferenceComponent} from "../conference/conference.component";
import {ProposalService} from "../shared/proposal.service";
import {ConferenceService} from "../shared/conference.service";
import {Router} from "@angular/router";
import {UserService} from "../shared/user.service";
import {ProposalAuthor} from "../shared/proposalAuthor.model";
import {Permission} from "../shared/permission.model";
import {Conference} from "../shared/conference.model";
import {User} from "../shared/user.model";
import {Proposal} from "../shared/proposal.model";

@Component({
  selector: 'app-proposal-new',
  templateUrl: './proposal-new.component.html',
  styleUrls: ['./proposal-new.component.css']
})
export class ProposalNewComponent implements OnInit {
  user : User = JSON.parse(sessionStorage.getItem("user"));
  conference : Conference = JSON.parse(sessionStorage.getItem("conference"));
  wantToAddAuthor :Boolean = false;
  proposal: Proposal;

  constructor(private conferenceService: ConferenceService,
              private proposalService: ProposalService,
              private userService : UserService,
              private router: Router) { }

  ngOnInit(): void {
  }

  addProposal(name: string, keywords: string, topics: string) {

    this.proposalService.addProposal(name, keywords, topics,
      this.conference)
      .subscribe(proposal => {
        this.proposal = proposal;
        this.proposalService.addAuthor(new ProposalAuthor(null, this.user, proposal));
        this.conferenceService.addPermission(new Permission(null, this.conference ,
          this.user, true, null, null))
          .subscribe(permission => console.log(permission));
      });
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

  goBack() {
    this.router.navigate(["conference/", this.conference.id]);
  }
}
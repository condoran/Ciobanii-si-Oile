import { Component, OnInit } from '@angular/core';
import {ConferenceComponent} from "../conference/conference.component";
import {ProposalService} from "../shared/proposal.service";
import {ConferenceService} from "../shared/conference.service";
import {Router} from "@angular/router";
import {UserService} from "../shared/user.service";
import {ProposalAuthor} from "../shared/proposalAuthor.model";
import {Permission} from "../shared/permission.model";

@Component({
  selector: 'app-proposal-new',
  templateUrl: './proposal-new.component.html',
  styleUrls: ['./proposal-new.component.css']
})
export class ProposalNewComponent implements OnInit {

  constructor(private conferenceService: ConferenceService,
              private proposalService: ProposalService,
              private router: Router) { }

  ngOnInit(): void {
  }

  addProposal(name: string, keywords: string, topics: string) {
    this.proposalService.addProposal(name, keywords, topics,
      ConferenceService.currentConference)
      .subscribe(proposal => {
        this.proposalService.addAuthor(new ProposalAuthor(null, UserService.currentUser,proposal));
        this.conferenceService.addPermission(new Permission(null, ConferenceService.currentConference,
          UserService.currentUser, true, null, null))
          .subscribe(permission => console.log(permission));
        console.log(proposal);
      });
    this.router.navigate(["conference/", ConferenceService.currentConference.id]);
  }
}

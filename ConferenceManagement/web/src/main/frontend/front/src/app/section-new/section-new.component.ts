import { Component, OnInit } from '@angular/core';
import {SectionService} from "../shared/section.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Conference} from "../shared/conference.model";
import {User} from "../shared/user.model";
import {Section} from "../shared/section.model";

@Component({
  selector: 'app-section-new',
  templateUrl: './section-new.component.html',
  styleUrls: ['./section-new.component.css']
})
export class SectionNewComponent implements OnInit {

  wantsToAddSectionChair: boolean = false;
  conference: Conference = JSON.parse(sessionStorage.getItem("conference"));
  candidatesForSectionChair: User[] = null;
  selectedCandidate: User = null;

  constructor(private sectionService: SectionService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if(+params["conferenceID"] != this.conference.id) {
        this.router.navigate(["conference/", this.conference.id, "newSection"]);
      }
    });
    this.sectionService.getCandidatesForSectionChair(this.conference.id)
      .subscribe( users => this.candidatesForSectionChair = users);
  }

  assignSectionSection(sectionName: string, user: User) {
    this.wantsToAddSectionChair = false;
    let section: Section = new Section(null, sectionName, user, null, this.conference);
    this.sectionService.addSection(section).subscribe();
  }

  setAsSectionChair(user: User) {
    this.selectedCandidate = user;
  }
}

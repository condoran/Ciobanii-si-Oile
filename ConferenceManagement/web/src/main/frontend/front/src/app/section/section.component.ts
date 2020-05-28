import {Component, Input, OnInit} from '@angular/core';
import {SectionService} from "../shared/section.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {Section} from "../shared/section.model";
import {switchMap} from "rxjs/operators";
import {Proposal} from "../shared/proposal.model";
import {ProposalService} from "../shared/proposal.service";

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {

  @Input() section: Section;
  proposalsToPresent: Proposal[] = null;

  constructor(private sectionService: SectionService,
              private route: ActivatedRoute,
              private location: Location,
              private proposalService: ProposalService) { }

  ngOnInit(): void {
    this.route.params.pipe(switchMap((params: Params) =>
      this.sectionService.getSectionByID(+params['sectionID'])))
      .subscribe(section => {this.section = section;
      this.proposalService.getProposalsForIDs(section.proposalIDs)
        .subscribe(proposals => {this.proposalsToPresent = proposals;
        console.log(this.proposalsToPresent)})});
  }

  goBack(): void{
    this.location.back();
  }

}

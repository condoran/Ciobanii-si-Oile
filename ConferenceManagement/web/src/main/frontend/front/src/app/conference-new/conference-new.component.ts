import { Component, OnInit } from '@angular/core';
import {ConferenceService} from "../shared/conference.service";
import {Conference} from "../shared/conference.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-conference-new',
  templateUrl: './conference-new.component.html',
  styleUrls: ['./conference-new.component.css']
})
export class ConferenceNewComponent implements OnInit {

  constructor(private conferenceService: ConferenceService,
              private router: Router) { }

  ngOnInit(): void {
  }

  addConference(name: string, callForPapers: string, startDate: string, endDate: string, bidding: string, abstract: string, full: string){
    let emptyList: number[] = [];
    let conference: Conference = new Conference(null, name, null, new Date(startDate), new Date(endDate), callForPapers, new Date(abstract), new Date(full), new Date(bidding), null, null, null, emptyList);
    console.log(conference);

    this.conferenceService.saveConference(conference).subscribe(conference => console.log(conference));
  }

  goBack() {
    this.router.navigate([""]);
  }
}

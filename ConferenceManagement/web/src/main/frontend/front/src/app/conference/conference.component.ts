import {Component, Input, OnInit} from '@angular/core';
import {Conference} from "../shared/conference.model";
import {ConferenceService} from "../shared/conference.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-conference',
  templateUrl: './conference.component.html',
  styleUrls: ['./conference.component.css']
})
export class ConferenceComponent implements OnInit {

  @Input() conference: Conference;

  constructor(private conferenceService: ConferenceService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
    this.route.params.pipe(switchMap((params: Params) => this.conferenceService.getConference(+params['conferenceID'])))
      .subscribe(conference => this.conference = conference);
  }

  goBack(): void{
    this.location.back();
  }

}

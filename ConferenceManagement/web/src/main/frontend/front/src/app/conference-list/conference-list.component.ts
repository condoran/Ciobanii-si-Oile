import { Component, OnInit } from '@angular/core';
import {ConferenceService} from '../shared/conference.service';
import {Conference} from '../shared/conference.model';

@Component({
  selector: 'app-conference-list',
  templateUrl: './conference-list.component.html',
  styleUrls: ['./conference-list.component.css']
})
export class ConferenceListComponent implements OnInit {
  conferences: Conference[];
  constructor(private conferenceService: ConferenceService) { }

  ngOnInit(): void {
    this.conferenceService.getConferences()
      .subscribe(conferences => this.conferences = conferences);

    // @ts-ignore
    console.log(Date.parse(this.conferences[0].startDate))
  }

}

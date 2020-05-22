import {Component, Input, OnInit} from '@angular/core';
import {Conference} from "../shared/conference.model";
import {ConferenceService} from "../shared/conference.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs/operators";
import {UserService} from "../shared/user.service";
import {User} from "../shared/user.model";

@Component({
  selector: 'app-conference',
  templateUrl: './conference.component.html',
  styleUrls: ['./conference.component.css']
})
export class ConferenceComponent implements OnInit {

  @Input() conference: Conference;
  user: User;

  constructor(private conferenceService: ConferenceService,
              private route: ActivatedRoute,
              private location: Location,
              private userService: UserService) { }

  ngOnInit(): void {
    this.route.params.pipe(switchMap((params: Params) => this.conferenceService.getConference(+params['conferenceID'])))
      .subscribe(conference => this.conference = conference);

    this.userService.getUserByUsername(sessionStorage.getItem('username')).subscribe(user => {this.user = user;
      console.log(user)});
  }

  goBack(): void{
    this.location.back();
  }

  updateConference(): void{
    console.log(this.conference.startDate);
    this.conferenceService.updateConference(this.conference).subscribe( _ => this.goBack());
  }

}

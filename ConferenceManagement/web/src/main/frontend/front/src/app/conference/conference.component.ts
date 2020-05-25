import {Component, Input, OnInit} from '@angular/core';
import {Conference} from "../shared/conference.model";
import {ConferenceService} from "../shared/conference.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs/operators";
import {UserService} from "../shared/user.service";
import {User} from "../shared/user.model";
import {Observable} from "rxjs";

@Component({
  selector: 'app-conference',
  templateUrl: './conference.component.html',
  styleUrls: ['./conference.component.css']
})
export class ConferenceComponent implements OnInit {

  @Input() conference: Conference;
  user: User;
  isChair: string;
  isCoChair: string;
  isLoggedIn: string;

  constructor(private conferenceService: ConferenceService,
              private route: ActivatedRoute,
              private router: Router,
              private location: Location,
              private userService: UserService) { }

  ngOnInit(): void {
    this.route.params.pipe(switchMap((params: Params) => this.conferenceService.getConference(+params['conferenceID'])))
      .subscribe(conference => this.conference = conference);

    this.isChair = sessionStorage.getItem('isChair');
    this.isCoChair = sessionStorage.getItem('isCoChair');
    this.isLoggedIn = sessionStorage.getItem('username');
  }

  getUser(): void{
    this.userService.getUserByUsername(sessionStorage.getItem('username')).subscribe(user => {this.user = user;
      console.log(this.user)});
  }

  goBack(): void{
    this.location.back();
  }

  goToCreateProposal(): void{
    this.router.navigate(["conference/", this.conference.id,"newProposal"]);
  }

  goToProposals(): void{
    this.router.navigate(["conference/", this.conference.id,"proposals"]);
  }

  updateConference(): void{
    console.log(this.conference.startDate);
    var date1 = (<HTMLInputElement>document.getElementById("startDate")).value;
    var date2 = (<HTMLInputElement>document.getElementById("endDate")).value;
    this.conference.startDate = new Date(date1);
    this.conference.endDate = new Date(date2);
    this.conferenceService.updateConference(this.conference).subscribe( _ => this.goBack());
  }

}

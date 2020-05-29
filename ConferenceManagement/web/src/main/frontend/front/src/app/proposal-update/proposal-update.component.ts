import {Component, ElementRef, Input, OnInit, Sanitizer, ViewChild} from '@angular/core';
import {ProposalService} from "../shared/proposal.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Proposal} from "../shared/proposal.model";
import {switchMap} from "rxjs/operators";
import {Location} from "@angular/common";
import {User} from "../shared/user.model";
import {Conference} from "../shared/conference.model";
import {ProposalAuthor} from "../shared/proposalAuthor.model";
import {Permission} from "../shared/permission.model";
import {UserService} from "../shared/user.service";
import {ConferenceService} from "../shared/conference.service";
import {DomSanitizer} from "@angular/platform-browser";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-proposal-update',
  templateUrl: './proposal-update.component.html',
  styleUrls: ['./proposal-update.component.css']
})
export class ProposalUpdateComponent implements OnInit {

  @ViewChild('fileUpload', {static: false}) fileUpload: ElementRef;
  file: File = null;
  fileName: string;

  @Input() proposal: Proposal;
  user : User = JSON.parse(sessionStorage.getItem("user"));
  conference : Conference = JSON.parse(sessionStorage.getItem("conference"));
  wantToAddAuthor :Boolean = false;
  formData: FormData;
  abstractUrl = this.sanitizer.bypassSecurityTrustResourceUrl('http://localhost:8080/proposal/getAbstract/' + this.proposal.id);
  file2: File = null;

  constructor(private proposalService: ProposalService,
              private route: ActivatedRoute,
              private location: Location,
              private userService: UserService,
              private conferenceService: ConferenceService,
              private router: Router,
              private sanitizer: DomSanitizer,
              private httpClient: HttpClient) {
    //this.httpClient.get('http://localhost:8080/proposal/getAbstract/'+this.proposal.id).subscribe( file => this.file2 = file);
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if(+params["conferenceID"] != this.conference.id) {
        this.router.navigate(["conference", this.conference.id, "proposals", +params['proposalID']]);
      }

      this.proposalService.getProposalForConference(+params['conferenceID'], +params['proposalID'])
        .subscribe(proposal => {this.proposal = proposal;})
    });
  }

  uploadAbstractButtonPressed() {
    const fileUpload = this.fileUpload.nativeElement;
    fileUpload.onchange = () => {
      this.file = fileUpload.files[0];
      this.fileName = this.file.name;
      this.fileUpload.nativeElement.value = '';
    };
    fileUpload.click();
  }

  updateProposal(): void {
    this.proposalService.uploadAbstractProper(this.proposal.id, this.file).subscribe(result => console.log(result))
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

  seeAbstract() {

  }
}

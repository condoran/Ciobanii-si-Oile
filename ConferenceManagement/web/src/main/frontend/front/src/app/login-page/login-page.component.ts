import { Component, OnInit } from '@angular/core';
import {UserService} from '../shared/user.service';
import {User} from '../shared/user.model';
import {Router} from "@angular/router";
import {MenuComponent} from "../menu/menu.component";
import {ProposalService} from "../shared/proposal.service";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  constructor(private userService: UserService,
              private router: Router,
              private menuComponent: MenuComponent,
              private proposalService: ProposalService) { }

  private user: User;
  private logInStatus: boolean;
  ngOnInit(): void {
  }

  login(username: string, password: string) {
    if (username === '' || password === '') {
      alert('Please fill the inputs!');
      return;
    }

    this.userService.login(username, password)
      .subscribe(result => {
        this.user = result;
        if(this.user === null){
          alert("Invalid username or password!");
        }
        else {
          sessionStorage.setItem("user", JSON.stringify(this.user));
          sessionStorage.setItem("username", username);
          sessionStorage.setItem("isChair", String(this.user.isChair));
          sessionStorage.setItem("isCoChair", String(this.user.isCoChair));
          this.proposalService.getProposalsIDsForUser(this.user.id)
            .subscribe(IDs => sessionStorage.setItem("proposalsIDs", JSON.stringify(IDs)))
          this.menuComponent.ngOnInit();
          this.router.navigate([""]);
        }
      });
  }
}

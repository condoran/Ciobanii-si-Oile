import { Component, OnInit } from '@angular/core';
import {UserService} from '../shared/user.service';
import {User} from '../shared/user.model';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  constructor(private userService: UserService) { }

  private user: User;
  ngOnInit(): void {
  }

  login(username: string, password: string) {
    if (username === '' || password === '') {
      alert('Please fill the inputs!');
    }

    this.userService.login(username, password)
      .subscribe(result => this.user = result);

    console.log(this.user);

    this.userService.getConferencesForPCMember(username)
      .subscribe(conference => console.log(conference));
    //todo: go to another page
  }
}

import { Component, OnInit } from '@angular/core';
import {UserService} from '../shared/user.service';
import {User} from '../shared/user.model';
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  private cookieValue: string;

  constructor(private userService: UserService,
              private cookieService: CookieService) { }

  private user: User;
  ngOnInit(): void {
  }

  login(username: string, password: string) {
    if (username === '' || password === '') {
      alert('Please fill the inputs!');
    }

    this.userService.login(username, password)
      .subscribe(result => this.user = result);

    const dateNow = new Date();
    dateNow.setSeconds(dateNow.getSeconds() + 10);

    this.cookieService.set('user', username, dateNow);
    console.log(this.cookieService.get('user'));

  }
}

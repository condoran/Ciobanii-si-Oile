import { Component, OnInit } from '@angular/core';
import {UserService} from '../shared/user.service';
import {User} from '../shared/user.model';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  // private user: User;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  login(username: string, password: string) {
    if (username === '' || password === '') {
      alert('BAAAA!');
    }
    let user: User;
    // const user: Observable<User> = this.userService.login2(username, password);
    this.userService.login2(username, password)
      .subscribe(x => {user = x; console.log(x.id); });
    // trimite in spate altfel !
    console.log(user);
    console.log(username, password);
  }
}

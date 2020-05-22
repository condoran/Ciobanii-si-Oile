import { Component, OnInit } from '@angular/core';
import {UserService} from '../shared/user.service';
import {User} from '../shared/user.model';
import {Router} from "@angular/router";
import {MenuComponent} from "../menu/menu.component";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  constructor(private userService: UserService,
              private router: Router,
              private menuComponent: MenuComponent) { }

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
        this.logInStatus = result;
        if(this.logInStatus === false){
          alert("Invalid username or password!");
        }
        else {
          sessionStorage.setItem("username", username);
          this.menuComponent.ngOnInit();
          this.router.navigate([""]);
        }
      });
  }
}

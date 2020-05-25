import { Component, OnInit } from '@angular/core';
import {LoginPageComponent} from "../login-page/login-page.component";
import {UserService} from "../shared/user.service";
import {User} from "../shared/user.model";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLogged: boolean;
  isChair: boolean;
  isCoChair: boolean;
  constructor(private userService : UserService) { }

  ngOnInit(): void {
    this.isLogged = sessionStorage.getItem('username') !== null;
    const user: User = JSON.parse(sessionStorage.getItem('user'));
    this.isChair = user.isChair;
    this.isCoChair = user.isCoChair;
  }

  logout(): void{
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('isChair');
    sessionStorage.removeItem('isCoChair');
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('conference');
    this.ngOnInit();
  }



}

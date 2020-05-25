import { Component, OnInit } from '@angular/core';
import {LoginPageComponent} from "../login-page/login-page.component";
import {UserService} from "../shared/user.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLogged: boolean;
  constructor(private userService : UserService) { }

  ngOnInit(): void {
    this.isLogged = sessionStorage.getItem('username') !== null;
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

import { Component, OnInit } from '@angular/core';
import {LoginPageComponent} from "../login-page/login-page.component";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLogged: boolean;
  constructor() { }

  ngOnInit(): void {
    this.isLogged = sessionStorage.getItem('username') !== null;
  }

  logout(): void{
    sessionStorage.removeItem('username');
    this.ngOnInit();
  }



}

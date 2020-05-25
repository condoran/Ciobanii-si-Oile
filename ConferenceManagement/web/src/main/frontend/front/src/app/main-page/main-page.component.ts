import { Component, OnInit } from '@angular/core';
import {User} from "../shared/user.model";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    if(sessionStorage.getItem("username") !== null){
      console.log("hello " + sessionStorage.getItem("username"));
      let user: User = JSON.parse(sessionStorage.getItem("user"));
      console.log(user);
    }
  }

}

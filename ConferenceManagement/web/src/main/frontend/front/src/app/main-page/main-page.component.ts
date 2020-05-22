import { Component, OnInit } from '@angular/core';

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
    }
  }

}

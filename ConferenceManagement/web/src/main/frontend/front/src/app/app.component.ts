import {Component, OnInit} from '@angular/core';
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'front';
  private cookieValue: string;

  constructor(private cookieService: CookieService) {
  }

  ngOnInit(): void {
    const dateNow = new Date();
    dateNow.setSeconds(dateNow.getSeconds() + 10);

    console.log(this.cookieService.get('user'));
    if(this.cookieService.get('user') !== "null" && this.cookieService.get('user').length !== 0){
      return;
    }
    this.cookieService.set('user', 'null', dateNow);

    this.cookieValue = this.cookieService.get('user');

  }

}

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  register(username: string, password: string) {
    if (username === '' || password === '') {
      alert('BAAAA!');
    }
    // trimite in spate altfel !
    console.log(username, password);
  }
}

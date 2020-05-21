import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  register(username: string, password: string) {
    if (username === '' || password === '') { alert('BAAAA!'); }
    // trimite in spate altfel !
    console.log(username, password);
  }

}

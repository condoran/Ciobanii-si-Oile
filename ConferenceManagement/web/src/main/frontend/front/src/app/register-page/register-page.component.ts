import { Component, OnInit } from '@angular/core';
import {UserService} from "../shared/user.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
  }

  registerAuthor(name:string, username: string, password: string, email: string) {
    if (username === '' || password === '' || name === '' || email === '') { alert('All fields must be filled!'); }
    // trimite in spate altfel !
    console.log(username, password);
  }

  registerPCMember(name:string, username: string, password: string, email: string, affiliation: string, website: string) {
    if (username === '' || password === '' || name === '' || email === '' || affiliation === '' || website === '') { alert('All fields must be filled!'); }
    // trimite in spate altfel !
    console.log(username, password);
  }

  showRegisterForm(x) {
    if(x === 0){
      document.getElementById("register_author").style.display='block';
      document.getElementById("register_pcmember").style.display='none';
    }
    else{
      document.getElementById("register_author").style.display='none';
      document.getElementById("register_pcmember").style.display='block';
    }
  }

}

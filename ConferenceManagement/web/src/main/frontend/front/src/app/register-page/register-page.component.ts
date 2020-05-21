import { Component, OnInit } from '@angular/core';
import {UserService} from "../shared/user.service";
import {Router} from "@angular/router";
import {User} from "../shared/user.model";
import {Observable} from "rxjs";


@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {
  user: User;
  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
  }

  register(name:string, username: string, password: string, email: string) {
    if (username === '' || password === '' || name === '' || email === '') {
      alert('All fields must be filled!');
      return;
    }

    this.userService.register(name, username, password, email).subscribe(user => this.user = user);

    if(this.user === null){
      alert("Username and email must be unique!");
      return;
    }

    this.router.navigate(["login-page"]);
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

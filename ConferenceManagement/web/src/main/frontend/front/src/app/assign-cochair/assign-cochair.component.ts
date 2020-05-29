import { Component, OnInit } from '@angular/core';
import {UserService} from "../shared/user.service";
import {User} from "../shared/user.model";

@Component({
  selector: 'app-assign-cochair',
  templateUrl: './assign-cochair.component.html',
  styleUrls: ['./assign-cochair.component.css']
})
export class AssignCochairComponent implements OnInit {

  usersToAssign: User[] = null;
  SCMembers: User[] = null;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUsersWithoutChairs()
      .subscribe(users => this.usersToAssign = users);
    this.userService.getSCMembers()
      .subscribe( users => this.SCMembers = users);
  }

  assignAsCoChair(user: User) {
    user.isCoChair = true;
    user.isSCMember = true;
    this.userService.updateUser(user).subscribe();
  }
}

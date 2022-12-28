import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './user.service';

@Component({
  selector: 'euprava-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  id : Object = {};

  constructor(private route: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getExample()
  }

  createA1Request(){
    this.route.navigate(['/a1-from']);
  }


}

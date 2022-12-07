import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { A1Request } from 'src/app/model/a1Request/a1Request';
import { UserService } from '../user.service';

@Component({
  selector: 'euprava-user-requests',
  templateUrl: './user-requests.component.html',
  styleUrls: ['./user-requests.component.css']
})
export class UserRequestsComponent implements OnInit {

  requests : A1Request[];


  constructor(private route: Router, private userService: UserService) {
    this.requests = [];

  }

  ngOnInit(): void {
    this.userService.getExample().subscribe((response) =>{

      this.requests.push(new A1Request(response));

    });

  }


}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ObrazacA1 } from 'src/app/model/a1Request/obrazacA1';
import { UserService } from '../user.service';


@Component({
  selector: 'euprava-user-requests',
  templateUrl: './user-requests.component.html',
  styleUrls: ['./user-requests.component.css']
})
export class UserRequestsComponent implements OnInit {

  requests : ObrazacA1[];
  xml : any;


  constructor(private route: Router, private userService: UserService) {
    this.requests = [];

  }

  ngOnInit(): void {
    const _this = this;
    var parseString = require('xml2js').parseString;
    this.userService.getExample().subscribe((result) =>{
      this.xml = result;
      parseString(result ,function(err: any, res: any) {

        console.log(res);
        console.log(res["obrazacA1"]["Podnosilac"][0]["$"]["xsi:type"])
        result = res;

        result["obrazacA1"]["Podnosilac"] = result["obrazacA1"]["Podnosilac"][0];
        result["obrazacA1"]["Punomocnik"] = result["obrazacA1"]["Punomocnik"][0];
      })

      console.log(result);

    });

  }


}

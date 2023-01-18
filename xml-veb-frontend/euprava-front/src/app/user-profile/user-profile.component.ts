import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './user.service';
import * as xml2js from 'xml2js';
import { RequestResponse } from '../model/a1Response';
import { saveAs } from 'file-saver';

@Component({
  selector: 'euprava-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  displayedColumns: string[] = ['id','submitterName' ,'type', 'submitDate', 'status', 'pdf', 'html' ];

  requests:RequestResponse[];

  loaded : boolean = false;

  id : Object = {};

  filter :string = '';

  constructor(private route: Router, private userService: UserService) {
    this.requests = [];
  }

  ngOnInit(): void {
    this.userService.getClientRequests().subscribe({
      next:(res) =>{
        xml2js.parseString(res, (err, result) => {
          const responseArray = result["a1ResponseList"]["a1Response"] as Array<Object>;
          if(responseArray === undefined){
            this.loaded = true;
            return;
          }
          responseArray.forEach((element: any) => {
            this.requests.push({'id':element["id"][0], 'submitterName':element["submitterName"][0],'status':element["status"][0],'submitDate':element["submitDate"][0],'type':element["type"][0]} as RequestResponse);
          });
       });
       this.loaded = true;
      }
    })
  }

  searchFilter(){

    if(this.filter.length > 0 ){
      this.loaded = false;
      this.requests = [];
      this.userService.getClientRequestsByParam(this.filter).subscribe({
        next:(res) =>{
          xml2js.parseString(res, (err, result) => {
            const responseArray = result["a1ResponseList"]["a1Response"] as Array<Object>;
            if(responseArray === undefined){
              this.loaded = true;
              return;
            }
            responseArray.forEach((element: any) => {
              this.requests.push({'id':element["id"][0],'submitterName':element["submitterName"][0], 'status':element["status"][0],'submitDate':element["submitDate"][0],'type':element["type"][0]} as RequestResponse);
            });
            this.loaded = true;
         });
        }
      });
    }
  }

  createA1Request(){
    this.route.navigate(['/a1-from']);
  }

  createP1Request() {
    this.route.navigate(['/p1-form']);
  }

  clear(){
    this.filter = '';
    this.loaded = false;
    this.userService.getClientRequests().subscribe({
      next:(res) =>{
        xml2js.parseString(res, (err, result) => {
          const responseArray = result["a1ResponseList"]["a1Response"] as Array<Object>;
          if(responseArray === undefined){
            this.loaded = true;
            return;
          }
          responseArray.forEach((element: any) => {
            this.requests.push({'id':element["id"][0],'submitterName':element["submitterName"][0], 'status':element["status"][0],'submitDate':element["submitDate"][0],'type':element["type"][0]} as RequestResponse);
          });
          this.loaded = true;
       });
      }
    })
  }

  downloadPDF(request:RequestResponse){
    this.userService.downloadPDF(request.id).subscribe(data => {
      saveAs(data, 'a1_'+request.id+ '.pdf');});
  }


  downloadHTML(request:any){
    this.userService.downloadHTML(request.id).subscribe(data => {
      saveAs(data, 'a1_'+request.id+ '.html');});
  }

}

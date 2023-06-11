import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from './user.service';
import * as xml2js from 'xml2js';
import {RequestResponse} from '../model/a1Response';
import {saveAs} from 'file-saver';

export interface Z1Request {
  id: string
  podnosilac: string
  punomocnik: string
  status: string
}

export type TableType = 'A1' | 'P1' | 'Z1'

@Component({
  selector: 'euprava-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  displayedColumns: string[] = ['id','submitterName' ,'type', 'submitDate', 'status', 'pdf', 'html' ,'rjesenje'];
  displayedColumnsZ1: string[] = ['id','podnosilac' ,'punomocnik', 'status'];

  requests: RequestResponse[];
  z1Requests: Z1Request[];
  tableType: TableType = 'A1'

  loaded : boolean = false;
  loadedZ1 : boolean = false;

  id : Object = {};

  filter :string = '';

  constructor(private route: Router, private userService: UserService) {
    this.requests = [];
    this.z1Requests = [];
  }

  handleTableChange(value: TableType) {
    this.tableType = value
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
    this.userService.getAllZ1().subscribe({
      next:(res) =>{
        xml2js.parseString(res, (err, result) => {
          const responseArray = result["z1ResponseList"]["z1Response"] as Array<Object>;
          if(responseArray === undefined){
            this.loadedZ1 = false;
            return;
          }
          responseArray.forEach((element: any) => {
            const newElement = { id: element['id'][0], podnosilac: element['podnosilac'][0], punomocnik: element['punomocnik'][0], status: element['status'][0] }
            this.z1Requests.push(newElement)
          });
        });
        this.loadedZ1 = true;
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

  createZ1Request() {
    this.route.navigate(['/z1-form'])
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
    this.userService.getAllZ1().subscribe({
      next:(res) =>{
        xml2js.parseString(res, (err, result) => {
          const responseArray = result["z1ResponseList"]["z1Response"] as Array<Object>;
          if(responseArray === undefined){
            this.loaded = true;
            return;
          }
          responseArray.forEach((element: any) => {
            // this.requests.push();
            console.log(element)
          });
        });
        this.loaded = true;
      }
    })
  }

  downloadPDF(request:RequestResponse){
    this.userService.downloadPDF(request.id).subscribe(data => {
      saveAs(data, 'a1_'+request.id+ '.pdf');});
  }

  getRjesenje(request:RequestResponse){
    this.userService.downloadRjesenjeByRequestId(request.id).subscribe((response:any) => {
      const url = window.URL.createObjectURL(response);
      const link = document.createElement('a');
      link.href = url;
      link.download = "rjesenje.pdf";
      link.click();
  });
  }


  downloadHTML(request:any){
    this.userService.downloadHTML(request.id).subscribe(data => {
      saveAs(data, 'a1_'+request.id+ '.html');});
  }

  protected readonly crypto = crypto;
}

import { Component, OnInit } from '@angular/core';
import { RequestResponse } from '../model/a1Response';
import { EmployeeService } from './employee.service';
import { saveAs } from 'file-saver';
import * as xml2js from 'xml2js';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { HandleRequestComponent } from './handle-request/handle-request.component';
import { MetadataDownloadComponent } from './metadata-download/metadata-download.component';
import { ReportComponent } from './report/report.component';

@Component({
  selector: 'euprava-employee-profile',
  templateUrl: './employee-profile.component.html',
  styleUrls: ['./employee-profile.component.css']
})
export class EmployeeProfileComponent implements OnInit {

  displayedColumns: string[] = ['id','submitterName' ,'type', 'submitDate', 'status', 'pdf', 'html','metadata','handle' ];

  requests:RequestResponse[] = [];

  loaded:boolean = false;

  filter :string = '';

  constructor(private employeeService : EmployeeService,public matDialog: MatDialog) {

  }

  ngOnInit(): void {
    this.employeeService.getRequests().subscribe({
      next:(res) =>{
        xml2js.parseString(res, (err, result) => {
          const responseArray = result["a1ResponseList"]["a1Response"] as Array<Object>;
          responseArray.forEach((element: any) => {
            this.requests.push({'id':element["id"][0],'email':element["email"][0] ,'submitterName':element["submitterName"][0], 'status':element["status"][0],'submitDate':element["submitDate"][0],'type':element["type"][0]} as RequestResponse);
          });
          this.loaded = true;
       });
      }
    })
  }

  searchFilter(){
    if(this.filter.length > 0 ){
      this.loaded = false;
      this.requests = [];
      this.employeeService.getRequestsByParam(this.filter).subscribe({
        next:(res) =>{
          xml2js.parseString(res, (err, result) => {
            const responseArray = result["a1ResponseList"]["a1Response"] as Array<Object>;
            if(responseArray === undefined){
              this.loaded = true;
              return;
            }
            responseArray.forEach((element: any) => {
              this.requests.push({'id':element["id"][0],'email':element["email"][0],'submitterName':element["submitterName"][0], 'status':element["status"][0],'submitDate':element["submitDate"][0],'type':element["type"][0]} as RequestResponse);
            });
            this.loaded = true;
         });
        }
      });
    }
  }

  searchFilterMeta(){
    if(this.filter.length > 0 ){
      this.loaded = false;
      this.requests = [];
      this.employeeService.getRequestsByMeta(this.filter).subscribe({
        next:(res) =>{
          xml2js.parseString(res, (err, result) => {
            const responseArray = result["a1ResponseList"]["a1Response"] as Array<Object>;
            if(responseArray === undefined){
              this.loaded = true;
              return;
            }
            responseArray.forEach((element: any) => {
              this.requests.push({'id':element["id"][0],'email':element["email"][0], 'submitterName':element["submitterName"][0], 'status':element["status"][0],'submitDate':element["submitDate"][0],'type':element["type"][0]} as RequestResponse);
            });
            this.loaded = true;
         });
        }
      });
    }
  }

  getRjesenje(request:RequestResponse){
    this.employeeService.downloadRjesenjeByRequestId(request.id).subscribe((response:any) => {
      const url = window.URL.createObjectURL(response);
      const link = document.createElement('a');
      link.href = url;
      link.download = "rjesenje.pdf";
      link.click();
  });
  }

  clear(){
    this.filter = '';
    this.loaded = false;
    this.employeeService.getRequests().subscribe({
      next:(res) =>{
        this.requests =[ ];
        xml2js.parseString(res, (err, result) => {
          const responseArray = result["a1ResponseList"]["a1Response"] as Array<Object>;
          responseArray.forEach((element: any) => {
            this.requests.push({'id':element["id"][0],'email':element["email"][0], 'submitterName':element["submitterName"][0], 'status':element["status"][0],'submitDate':element["submitDate"][0],'type':element["type"][0]} as RequestResponse);
          });
          this.loaded = true;
       });
      }
    })
  }

  downloadPDF(request:RequestResponse){
    this.employeeService.downloadPDF(request.id).subscribe(data => {
      saveAs(data, 'a1_'+request.id+ '.pdf');});
  }

  handle(request:RequestResponse){
    const dialogConfig = new MatDialogConfig();
    // The user can't close the dialog by clicking outside its body
    dialogConfig.disableClose = false;
    dialogConfig.id = "handle-modal";
    dialogConfig.data = {request};
    dialogConfig.height = "60%";
    dialogConfig.width = "30%";
    // https://material.angular.io/components/dialog/overview
    const dialogRef = this.matDialog.open(HandleRequestComponent, dialogConfig);
    dialogRef.afterClosed().subscribe({
      complete:()=>{
          this.clear();
          }
        });

  }

  toggleReport(){
    const dialogConfig = new MatDialogConfig();
    // The user can't close the dialog by clicking outside its body
    dialogConfig.disableClose = false;
    dialogConfig.id = "report-modal";
    dialogConfig.height = "50%";
    dialogConfig.width = "30%";
    // https://material.angular.io/components/dialog/overview
    const dialogRef = this.matDialog.open(ReportComponent, dialogConfig);
    dialogRef.afterClosed().subscribe({
      complete:()=>{
          this.clear();
          }
        });

  }

  toggleMetadaDownload(request:RequestResponse){
    const dialogConfig = new MatDialogConfig();
    // The user can't close the dialog by clicking outside its body
    dialogConfig.disableClose = false;
    dialogConfig.id = "metadata-modal";
    dialogConfig.data = {request};
    dialogConfig.height = "13%";
    dialogConfig.width = "15%";
    // https://material.angular.io/components/dialog/overview
    const dialogRef = this.matDialog.open(MetadataDownloadComponent, dialogConfig);
    dialogRef.afterClosed().subscribe({
      complete:()=>{

          }
        });

  }


  downloadHTML(request:any){
    this.employeeService.downloadHTML(request.id).subscribe(data => {
      saveAs(data, 'a1_'+request.id+ '.html');});
  }

  searchReference(){
    if(this.filter.length>0){
      this.loaded = false;
      this.requests = [];
      this.employeeService.getRequestsByReference(this.filter).subscribe({
        next:(res) =>{
          xml2js.parseString(res, (err, result) => {
            const responseArray = result["a1ResponseList"]["a1Response"] as Array<Object>;
            if(responseArray === undefined){
              this.loaded = true;
              return;
            }
            responseArray.forEach((element: any) => {
              this.requests.push({'id':element["id"][0],'email':element["email"][0], 'submitterName':element["submitterName"][0], 'status':element["status"][0],'submitDate':element["submitDate"][0],'type':element["type"][0]} as RequestResponse);
            });
            this.loaded = true;
         });
        }
      });
    }
  }
}

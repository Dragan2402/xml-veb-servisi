import {Component, OnInit} from '@angular/core';
import {RequestResponse} from '../model/a1Response';
import {EmployeeService} from './employee.service';
import {saveAs} from 'file-saver';
import * as xml2js from 'xml2js';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {HandleRequestComponent} from './handle-request/handle-request.component';
import {MetadataDownloadComponent} from './metadata-download/metadata-download.component';
import {ReportComponent} from './report/report.component';
import {P1Request, TableType, Z1Request} from "../user-profile/user-profile.component";
import {UserService} from "../user-profile/user.service";

@Component({
  selector: 'euprava-employee-profile',
  templateUrl: './employee-profile.component.html',
  styleUrls: ['./employee-profile.component.css']
})
export class EmployeeProfileComponent implements OnInit {

  displayedColumns: string[] = ['id','submitterName' ,'type', 'submitDate', 'status', 'pdf', 'html','metadata','handle' ];
  displayedColumnsZ1: string[] = ['id','podnosilac' ,'punomocnik', 'status', 'pdf', 'html', 'odobrenje', 'odbijanje'];
  displayedColumnsP1: string[] = ['brojPrijave', 'nazivPronalaska', 'podnosilac', 'priznatiDatumPodnosenja', 'pdf', 'html', 'odobrenje', 'odbijanje'];

  requests:RequestResponse[] = [];
  z1Requests: Z1Request[] = [];
  p1Requests: P1Request[] = [];
  tableType: TableType = 'A1'

  loaded:boolean = false;
  loadedZ1 : boolean = false;
  loadedP1 : boolean = false;

  filter :string = '';

  constructor(private employeeService : EmployeeService,public matDialog: MatDialog, private userService: UserService) {
  }

  handleTableChange(value: TableType) {
    this.tableType = value
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

    this.userService.getAllP1().subscribe({
      next:(res) =>{
        xml2js.parseString(res, (err, result) => {
          const responseArray = result["obrazacP1SearchResponseList"]["obrazacP1SearchResponse"] as Array<Object>;
          if(responseArray === undefined){
            this.loadedP1 = true;
            return;
          }
          responseArray.forEach((element: any) => {
            let podnosilac = "";

            if (element['podnosilac'][0]['ns2:Poslovno_ime'] != undefined) {
              podnosilac = element['podnosilac'][0]['ns2:Poslovno_ime'][0];

            } else if (element['podnosilac'][0]['ns2:Ime'] != undefined && element['podnosilac'][0]['ns2:Prezime'] != undefined) {
              podnosilac = element['podnosilac'][0]['ns2:Ime'] + " " + element['podnosilac'][0]['ns2:Prezime'];
            }

            const newElement: P1Request = {
              brojPrijave: element['brojPrijave'][0],
              nazivPronalaska: element['nazivPronalaska'][0]['ns2:Na_srpskom'][0]['_'],
              podnosilac: podnosilac,
              priznatiDatumPodnosenja: element['priznatiDatumPodnosenja'][0],
              status: element['status'][0]['_']
            }
            this.p1Requests.push(newElement)
          });
          this.loadedP1 = true;
        });
      }
    });

  }

  searchFilter(){
    if (this.tableType === 'A1') {
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

    else if (this.tableType === 'Z1' && this.filter !== '') {
      this.loadedZ1 = false;
      this.z1Requests = [];
      this.userService.getFilteredZ1(this.filter).subscribe({
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
    else if (this.tableType == 'P1') {
      this.loadedP1 = false;
      this.p1Requests = [];
      this.userService.getAllP1ByText(this.filter)
        .subscribe({
          next:(res) => {
            xml2js.parseString(res, (err, result) => {
              const responseArray = result["obrazacP1SearchResponseList"]["obrazacP1SearchResponse"] as Array<Object>;
              if(responseArray === undefined){
                this.loadedP1 = true;
                return;
              }
              responseArray.forEach((element: any) => {
                let podnosilac = "";

                if (element['podnosilac'][0]['ns2:Poslovno_ime'] != undefined) {
                  podnosilac = element['podnosilac'][0]['ns2:Poslovno_ime'][0];

                } else if (element['podnosilac'][0]['ns2:Ime'] != undefined && element['podnosilac'][0]['ns2:Prezime'] != undefined) {
                  podnosilac = element['podnosilac'][0]['ns2:Ime'] + " " + element['podnosilac'][0]['ns2:Prezime'];
                }

                const newElement: P1Request = {
                  brojPrijave: element['brojPrijave'][0],
                  nazivPronalaska: element['nazivPronalaska'][0]['ns2:Na_srpskom'][0]['_'],
                  podnosilac: podnosilac,
                  priznatiDatumPodnosenja: element['priznatiDatumPodnosenja'][0],
                  status: element['status'][0]['_']
                }
                this.p1Requests.push(newElement)
              });
              this.loadedP1 = true;
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

    this.userService.getAllZ1().subscribe({
      next:(res) =>{
        xml2js.parseString(res, (err, result) => {
          const responseArray = result["z1ResponseList"]["z1Response"] as Array<Object>;
          if(responseArray === undefined){
            this.loadedZ1 = false;
            return;
          }
          const temp: Z1Request[] = []
          responseArray.forEach((element: any) => {
            const newElement = { id: element['id'][0], podnosilac: element['podnosilac'][0], punomocnik: element['punomocnik'][0], status: element['status'][0] }
            temp.push(newElement)
          });
          this.z1Requests = temp
        });
        this.loadedZ1 = true;
      }
    })

    this.userService.getAllOdobreniP1().subscribe({
      next:(res) =>{
        xml2js.parseString(res, (err, result) => {
          const responseArray = result["obrazacP1SearchResponseList"]["obrazacP1SearchResponse"] as Array<Object>;
          if(responseArray === undefined){
            this.loadedP1 = true;
            return;
          }
          const temp: P1Request[] = []
          responseArray.forEach((element: any) => {
            let podnosilac = "";

            if (element['podnosilac'][0]['ns2:Poslovno_ime'] != undefined) {
              podnosilac = element['podnosilac'][0]['ns2:Poslovno_ime'][0];

            } else if (element['podnosilac'][0]['ns2:Ime'] != undefined && element['podnosilac'][0]['ns2:Prezime'] != undefined) {
              podnosilac = element['podnosilac'][0]['ns2:Ime'] + " " + element['podnosilac'][0]['ns2:Prezime'];
            }
            const newElement: P1Request = {
              brojPrijave: element['brojPrijave'][0],
              nazivPronalaska: element['nazivPronalaska'][0]['ns2:Na_srpskom'][0]['_'],
              podnosilac: podnosilac,
              priznatiDatumPodnosenja: element['priznatiDatumPodnosenja'][0],
              status: element['status'][0]['_']
            }
            temp.push(newElement)
          });
          this.p1Requests = temp
        });
        this.loadedP1 = true;
      }
    });

  }

  downloadPDF(request:RequestResponse){
    this.employeeService.downloadPDF(request.id).subscribe(data => {
      saveAs(data, 'a1_'+request.id+ '.pdf');});
  }

  downloadZ1PDF(request: Z1Request){
    this.userService.downloadZ1PDF(request.id).subscribe(data => {
      saveAs(data, 'z1_'+request.id+ '.pdf');});
  }


  downloadP1PDF(request: P1Request){
    let documentId = request.brojPrijave.split('/').join('-');
    this.userService.downloadP1PDF(documentId)
      .subscribe(data => {
        saveAs(data, 'p1_' + documentId + '.pdf');
      });
  }

  downloadZ1HTML(request:Z1Request){
    this.userService.downloadZ1HTML(request.id).subscribe(data => {
      saveAs(data, 'z1_'+request.id+ '.html');});
  }

  downloadP1HTML(request: P1Request){
    let documentId = request.brojPrijave.split('/').join('-');
    this.userService.downloadP1HTML(documentId)
      .subscribe(data => {
        saveAs(data, 'p1_' + documentId + '.html');
      });
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

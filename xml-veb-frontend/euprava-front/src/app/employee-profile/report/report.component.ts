import {Component, Inject, Input, OnInit} from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { EmployeeService } from '../employee.service';
import * as xml2js from 'xml2js';
import html2canvas from 'html2canvas';
import jspdf from 'jspdf';


@Component({
  selector: 'euprava-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  startDate!:Date;
  endDate!:Date;
  generated:boolean = false;

  approved:number = 0;
  declined:number = 0;
  waiting:number = 0;
  total:number = 0;



  constructor(public dialogRef: MatDialogRef<ReportComponent>, private employeService : EmployeeService, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }

  generate(){
    if(this.startDate===undefined || this.endDate === undefined){
      return;
    }
    const startDate = this.formatDate(this.startDate);
    const endDate = this.formatDate(this.endDate);
    this.employeService.getCountOfRequests(startDate, endDate, this.data.tableType.toLowerCase()).subscribe({
      next:(response)=>{
        xml2js.parseString(response, (err, result) => {
          this.approved = result["numberResponse"]["odobrenih"][0] as number;
          this.declined = result["numberResponse"]["odbijenih"][0] as number;
          this.waiting = result["numberResponse"]["podnesenih"][0] as number;
          this.total = <number>this.approved*1 + <number>this.declined*1 + <number>this.waiting*1;
          this.generated = true;
          var doc = new jspdf('l');
          doc.text(`\t\t\tIZVESTAJ ZA RASPON: ${startDate} - ${endDate}\n\n\n\tPodnesenih: ${this.waiting} \n\n\tOdobrenih: ${this.approved} \n\n\tOdbijenih: ${this.declined} \n\n\tUkupno: ${this.total}`
          +`\n\n\n\n\tIzvestaj podneo: ${localStorage.getItem("firstName")} ${localStorage.getItem("lastName")}`, 20,20);
          doc.save("izvestaj.pdf");
        })}});

  }

  formatDate(date:Date) {
    return [date.getFullYear(),
      this.padTo2Digits(date.getMonth() + 1),
      this.padTo2Digits(date.getDate())].join("-");

  }

  padTo2Digits(num:number) {
    return num.toString().padStart(2, '0');
  }

}

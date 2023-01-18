import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RequestResponse } from 'src/app/model/a1Response';
import { EmployeeService } from '../employee.service';
import * as xml2js from 'xml2js';

@Component({
  selector: 'euprava-handle-request',
  templateUrl: './handle-request.component.html',
  styleUrls: ['./handle-request.component.css']
})
export class HandleRequestComponent implements OnInit {

  approve:boolean = true;
  code:string = '';
  reason:string = '';
  request:RequestResponse;

  constructor(public dialogRef: MatDialogRef<HandleRequestComponent>,@Inject(MAT_DIALOG_DATA) public data: any, private employeService : EmployeeService) {
    this.request = data["request"];
   }

  ngOnInit(): void {
  }

  submit(){
    if(this.request ===undefined){
      return;
    }
    let requestBody = '<?xml version="1.0" encoding="UTF-8"?> '+
    '<rjesenje xmlns="http://users.com/model/rjesenje" '+
     'xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">'+
     '<id_zahtjeva>'+this.request.id+'</id_zahtjeva>'+
     '<tip_zahtjeva>'+this.request.type+'</tip_zahtjeva>'+
     '<ime_sluzbenika>'+localStorage.getItem("firstName")+'</ime_sluzbenika>'+
     '<prezime_sluzbenika>'+localStorage.getItem("lastName")+'</prezime_sluzbenika>';

    if(this.approve){
      if(this.code.length === 0){
        console.log("WRITE CODE");
        return;
      }
      requestBody = requestBody +'<slucaj>Odobravanje</slucaj><sifra>'+this.code+'</sifra></rjesenje>';
      this.employeService.createRjesenje(requestBody).subscribe({
        next:(v) =>{
          xml2js.parseString(v, (err, result) => {
            this.employeService.approveRequest(this.request.id,result["codeResponse"]["code"][0]).subscribe();
            this.dialogRef.close();
          });
        }
      });
    }else{
      if(this.reason.length === 0){
        console.log("WRITE REASON");
        return;
      }
      requestBody = requestBody+ '<slucaj>Odbijanje</slucaj><obrazlozenje>'+this.reason+'</obrazlozenje></rjesenje>';
      this.employeService.createRjesenje(requestBody).subscribe({
        next:(v)=>{
          this.employeService.declineRequest(this.request.id).subscribe();
          this.dialogRef.close();
        }
      })
    }
  }

}

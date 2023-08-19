import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {EmployeeService} from '../employee.service';
import * as xml2js from 'xml2js';
import {Z1Request} from "../../user-profile/user-profile.component";

@Component({
  selector: 'euprava-handle-request',
  templateUrl: './handle-request.component.html',
  styleUrls: ['./handle-request.component.css']
})
export class HandleRequestComponent implements OnInit {

  approve:boolean = true;
  code:string = '';
  reason:string = '';
  zavod: {
    primerakZnaka: string;
    spisakRobe: string;
    punomocje: string;
    punomocjeRanije: string;
    punomocjeNaknadno: string;
    opstiAkt: string;
    pravoPrvenstva: string;
    uplataTakse: string;
  } = {
    primerakZnaka: '',
    spisakRobe: '',
    punomocje: '',
    punomocjeRanije: '',
    punomocjeNaknadno: '',
    opstiAkt: '',
    pravoPrvenstva: '',
    uplataTakse: '',
  }
  request:Z1Request;

  constructor(public dialogRef: MatDialogRef<HandleRequestComponent>,@Inject(MAT_DIALOG_DATA) public data: any, private employeService : EmployeeService) {
    this.request = data["request"];
   }

  ngOnInit(): void {
  }

  convertToXML(data: any) {
    let xml = '<Z1ZavodRequest>';
    function createXML(obj: any) {
      Object.entries(obj).forEach(([key, value]) => {
        if (Array.isArray(value)) {
          value.forEach((item) => {
            xml += `<${key}>${item}</${key}>`;
          });
        } else if (typeof value === 'object') {
          xml += `<${key}>`;
          createXML(value);
          xml += `</${key}>`;
        } else {
          xml += `<${key}>${value}</${key}>`;
        }
      });
    }
    createXML(data);
    xml += '</Z1ZavodRequest>'
    return xml;
  }

  submit(){
    if(this.request ===undefined){
      return;
    }
    let requestBody = '<?xml version="1.0" encoding="UTF-8"?> '+
    '<rjesenje xmlns="http://users.com/model/rjesenje" '+
     'xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">'+
     '<id_zahtjeva>'+this.request.id+'</id_zahtjeva>'+
     '<tip_zahtjeva>Z1</tip_zahtjeva>'+
     '<ime_sluzbenika>'+localStorage.getItem("firstName")+'</ime_sluzbenika>'+
     '<prezime_sluzbenika>'+localStorage.getItem("lastName")+'</prezime_sluzbenika>';

    if(this.approve){
      if(this.code.length === 0){
        console.log("WRITE CODE");
        return;
      }
      requestBody = requestBody +'<slucaj>Odobravanje</slucaj><sifra>'+this.code+'</sifra></rjesenje>';
      this.employeService.createResenje(requestBody).subscribe({
        next:(v) =>{
          xml2js.parseString(v, (err, result) => {
            this.employeService.odobriZ1(this.request.id, this.convertToXML(this.zavod),result["createRjesenjeResponse"]["id"][0], this.code).subscribe();
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
      this.employeService.createResenje(requestBody).subscribe({
        next:(v)=>{
          xml2js.parseString(v, (err, result) => {
            this.employeService.odbijZ1(this.request.id, this.convertToXML(this.zavod),result["createRjesenjeResponse"]["id"][0]).subscribe();
            this.dialogRef.close();
          });
        }
      })
    }
  }
}

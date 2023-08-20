import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {EmployeeService} from '../employee.service';
import * as xml2js from 'xml2js';
import {Z1Request} from "../../user-profile/user-profile.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'euprava-handle-request',
  templateUrl: './handle-request.component.html',
  styleUrls: ['./handle-request.component.css']
})
export class HandleRequestComponent implements OnInit {

  forma: FormGroup;
  request:Z1Request;

  constructor(private formBuilder: FormBuilder, public dialogRef: MatDialogRef<HandleRequestComponent>,@Inject(MAT_DIALOG_DATA) public data: any, private employeService : EmployeeService) {
    this.request = data["request"];

    this.forma = this.formBuilder.group({
      approve: true,
      code: ['12345', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
      reason: ['Nije ispravna forma', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
      zavod: this.formBuilder.group({
        primerakZnaka: ['123', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
        spisakRobe: ['345', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
        punomocje: ['567', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
        punomocjeRanije: ['DA', [ Validators.required ]],
        punomocjeNaknadno: ['NE', [ Validators.required ]],
        opstiAkt: ['789', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
        pravoPrvenstva: ['987', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
        uplataTakse: ['765', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
      })
    })

    this.updateValidators();

   }

  updateValidators() {
    if (this.forma.value["approve"]) {
      this.forma.get('code')?.enable();
      this.forma.get('reason')?.disable();
    } else {
      this.forma.get('code')?.disable();
      this.forma.get('reason')?.enable();
    }
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
    if (!this.forma.valid) {
      return
    }
    let requestBody = '<?xml version="1.0" encoding="UTF-8"?> '+
    '<rjesenje xmlns="http://users.com/model/rjesenje" '+
     'xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">'+
     '<id_zahtjeva>'+this.request.id+'</id_zahtjeva>'+
     '<tip_zahtjeva>Z1</tip_zahtjeva>'+
     '<ime_sluzbenika>'+localStorage.getItem("firstName")+'</ime_sluzbenika>'+
     '<prezime_sluzbenika>'+localStorage.getItem("lastName")+'</prezime_sluzbenika>';

    if(this.forma.value['approve']){
      requestBody = requestBody +'<slucaj>Odobravanje</slucaj><sifra>'+this.forma.value['code']+'</sifra></rjesenje>';
      this.employeService.createResenje(requestBody).subscribe({
        next:(v) =>{
          xml2js.parseString(v, (err, result) => {
            this.employeService.odobriZ1(this.request.id, this.convertToXML(this.forma.value['zavod']),result["createRjesenjeResponse"]["id"][0], this.forma.value['code']).subscribe();
            this.dialogRef.close();
          });
        }
      });
    }else{
      requestBody = requestBody+ '<slucaj>Odbijanje</slucaj><obrazlozenje>'+this.forma.value['reason']+'</obrazlozenje></rjesenje>';
      this.employeService.createResenje(requestBody).subscribe({
        next:(v)=>{
          xml2js.parseString(v, (err, result) => {
            this.employeService.odbijZ1(this.request.id, this.convertToXML(this.forma.value['zavod']),result["createRjesenjeResponse"]["id"][0]).subscribe();
            this.dialogRef.close();
          });
        }
      })
    }
  }
}

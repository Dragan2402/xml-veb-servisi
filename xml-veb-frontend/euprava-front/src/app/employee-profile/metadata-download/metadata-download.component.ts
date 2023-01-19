import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RequestResponse } from 'src/app/model/a1Response';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'euprava-metadata-download',
  templateUrl: './metadata-download.component.html',
  styleUrls: ['./metadata-download.component.css']
})
export class MetadataDownloadComponent implements OnInit {

  request:RequestResponse;

  constructor(public dialogRef: MatDialogRef<MetadataDownloadComponent>,@Inject(MAT_DIALOG_DATA) public data: any, private employeService : EmployeeService) {
    this.request = data["request"];
   }

  ngOnInit(): void {
  }

  downloadJson(){
    this.employeService.getJson(this.request.id).subscribe((response:any) => {
      const url = window.URL.createObjectURL(response);
      const link = document.createElement('a');
      link.href = url;
      link.download = "file.json";
      link.click();
  });
  }

  downloadRdf(){
    this.employeService.getRdf(this.request.id).subscribe((response:any) => {
      const url = window.URL.createObjectURL(response);
      const link = document.createElement('a');
      link.href = url;
      link.download = "file.rdf";
      link.click();
  });
  }

}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http:HttpClient) { }

  downloadPDF(id:number){
    return this.http.get("/api/a1/downloadPDFById?id="+id, { responseType: 'blob' });
  }

  downloadHTML(id:number){
    return this.http.get("/api/a1/downloadHTMLById?id="+id, { responseType: 'blob' });
  }

  getRequestsByParam(param:string){
    const body = "<searchRequest><param>"+param+"</param></searchRequest>";
    return this.http.post("/api/a1/searchEmployeeByParam",body, {observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}})
  }

  getRequests(){
    return this.http.get("/api/a1/getRequests",{observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}});
  }

  createRjesenje(body:string){
    return this.http.post("/rjesenje",body, {observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}})
  }

  approveRequest(id:number, code:number){
    return this.http.get("/api/a1/approveRequest?id="+id+"&code="+code,{observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}});
  }

  declineRequest(id:number){
    return this.http.get("/api/a1/declineRequest?id="+id,{observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}});
  }
}

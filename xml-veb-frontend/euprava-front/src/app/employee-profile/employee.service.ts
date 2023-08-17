import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http:HttpClient) { }

  odobriZ1(documentId: string) {
    return this.http.put(`/api/z1/${documentId}/odobri`, {});
  }

  odbijZ1(documentId: string) {
    return this.http.put(`/api/z1/${documentId}/odbij`, {});
  }

  odobriP1(documentId: string) {
    return this.http.put(`/api/p1/${documentId}/odobri`, {});
  }

  odbijP1(documentId: string) {
    return this.http.put(`/api/p1/${documentId}/odbij`, {});
  }

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

  getRequestsByMeta(param:string){
    const body = "<searchRequest><param>"+param+"</param></searchRequest>";
    return this.http.post("/api/a1/searchMetadataByLogicalParams",body, {observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}})
  }

  getRequestsByReference(param:string){
    const body = "<searchRequest><param>"+param+"</param></searchRequest>";
    return this.http.post("/api/a1/searchEmployeeByReference",body, {observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}})
  }

  sendEmail(email:string, rjesenjeId:string){
    const body = `<emailRequest><email>${email}</email><id>${rjesenjeId}</id></emailRequest>`;
    return this.http.put("/rjesenje/sendEmail", body, {observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}})
  }

  getRequests(){
    return this.http.get("/api/a1/getRequests",{observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}});
  }

  getCountOfRequests(start:string, end:string, type: string = 'z1'){
    return this.http.get(`/api/${type}/getNumberOfRequestsForReport?start=${start}&&end=${end}`,{observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}});
  }

  createRjesenje(body:string){
    return this.http.post("/rjesenje",body, {observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}})
  }

  approveRequest(id:number, code:number,rjesenjeId:number){
    return this.http.get("/api/a1/approveRequest?id="+id+"&code="+code+"&idRjesenja="+rjesenjeId,{observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}});
  }

  declineRequest(id:number,rjesenjeId:number){
    return this.http.get("/api/a1/declineRequest?id="+id+"&idRjesenja="+rjesenjeId,{observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}});
  }

  getRdf(id: number) {
    const options = {
        headers: new HttpHeaders().append('Content-Type', 'application/rdf+xml'),
        responseType: 'blob' as 'json'
    };
    return this.http.get(`/api/a1/getRdfMetadata?id=`+id, options);
  }

  downloadRjesenjeByRequestId(id:number){
    const options = {
        headers: new HttpHeaders().append('Content-Type', 'application/pdf'),
        responseType: 'blob' as 'json'
    };
    return this.http.get(`/rjesenje/getRjesenjePdf?requestId=`+id, options);
  }

  getJson(id: number) {
    const options = {
        headers: new HttpHeaders().append('Content-Type', 'application/json'),
        responseType: 'blob' as 'json'
    };
    return this.http.get(`/api/a1/getJsonMetadata?id=`+id, options);
  }
}

import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http:HttpClient) { }

  odobriZ1(documentId: string, z1ZavodRequest: any, idResenja: string, brojPrijave: string) {
    return this.http.put(`/api/z1/${documentId}/odobri?idResenja=${idResenja}&brojPrijave=${brojPrijave}`, z1ZavodRequest, {observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}});
  }

  odbijZ1(documentId: string, z1ZavodRequest: any, idResenja: string) {
    return this.http.put(`/api/z1/${documentId}/odbij?idResenja=${idResenja}`, z1ZavodRequest, {observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}});
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

  createResenje(body:string){
    return this.http.post("/rjesenje",body, {observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}})
  }

  getRdf(id: number) {
    const options = {
        headers: new HttpHeaders().append('Content-Type', 'application/rdf+xml'),
        responseType: 'blob' as 'json'
    };
    return this.http.get(`/api/a1/getRdfMetadata?id=`+id, options);
  }

  downloadResenjeByRequestId(id:number){
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

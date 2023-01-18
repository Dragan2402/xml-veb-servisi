import { HttpClient, HttpHeaders,  } from '@angular/common/http';
import { Injectable , Inject} from '@angular/core';
import { map, Observable, throwError } from 'rxjs';
import axios from 'axios';
import { Entity } from '../model/p1Request/Entity';
import {  Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(
    private http: HttpClient, private router:Router) {
     }


  getExample(): Observable<any> {
    return this.http.get("/api/a1",{responseType: "text" as "json"});

  }

  SubmitA1Request(request: String, descriptionFile?: File, exampleFile? : File) {
    const headers = { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'};
    if(descriptionFile===undefined && exampleFile===undefined){
      request = request + "</obrazacA1>";
      axios.post('/api/a1',request, {headers}).then(response => {console.log(response);this.router.navigate(["/userProfile"]);});
    }else{
      if(descriptionFile !==undefined){
        const formData = new FormData();
        formData.append('file',<File>descriptionFile, (<File>descriptionFile).name);
        axios.post('/api/a1/uploadDescriptionFile',formData).then((response) =>{
          request = request + "<Sifra_Opisa>"+response.data+"</Sifra_Opisa>";
          if(exampleFile !==undefined){
            const exampleFormData = new FormData();
            exampleFormData.append('file',<File>exampleFile, (<File>exampleFile).name);
            axios.post('/api/a1/uploadExampleFile',exampleFormData).then((response) =>{
              request = request + "<Sifra_Primjera>"+response.data+"</Sifra_Primjera>";
              request = request + "</obrazacA1>";
              axios.post('/api/a1',request, {headers}).then(response =>{
                console.log(response);
                this.router.navigate(["/userProfile"]);

              });
            });
          }else{
            request = request + "</obrazacA1>";
            axios.post('/api/a1',request, {headers}).then(resposne =>{
              console.log(resposne);
              this.router.navigate(["/userProfile"]);
            });
          }
        })
      }else{
        const exampleFormData = new FormData();
        exampleFormData.append('file',<File>exampleFile, (<File>exampleFile).name);
        axios.post('/api/a1/uploadExampleFile',exampleFormData).then((response) =>{
          request = request + "<Sifra_Primjera>"+response.data+"</Sifra_Primjera>";
          request = request + "</obrazacA1>";
          axios.post('/api/a1',request, {headers}).then(response =>{
            console.log(response);
            this.router.navigate(["/userProfile"]);
          });
        });
      }
    }
  }

  sendXml(entity: string) {
    const headers = { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'};
    axios.post("/api/p1", entity, {headers})
    .then(response =>{
        console.log(response.data);
      });
  }

  loadXml(documentId: any) {
    // const headers = { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'};
    return this.http.get(`/api/p1/${documentId}`, {observe: "body", responseType: "text", headers: { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'}});
  }
}

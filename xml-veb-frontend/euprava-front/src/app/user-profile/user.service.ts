import { HttpClient, HttpHeaders,  } from '@angular/common/http';
import { Injectable , Inject} from '@angular/core';
import { map, Observable, throwError } from 'rxjs';
import { AppConfig } from '../AppConfig/appconfig.interface';
import { APP_SERVICE_CONFIG } from '../AppConfig/appconfig.service';
import axios from 'axios';


@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient) {
     }


  getExample(): Observable<any> {
    return this.http.get("/api/a1",{responseType: "text" as "json"});

  }

  SubmitA1Request(request: String, descriptionFile?: File, exampleFile? : File) {
    const headers = { 'Content-Type': 'application/xml' , 'Accept': 'application/xml'};
    if(descriptionFile===undefined && exampleFile===undefined){
      request = request + "</obrazacA1>";
      axios.post('/api/a1',request, {headers}).then(response => {console.log(response)});
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
              });
            });
          }else{
            request = request + "</obrazacA1>";
            axios.post('/api/a1',request, {headers}).then(resposne =>{
              console.log(resposne);
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
          });
        });
      }
    }
  }
}

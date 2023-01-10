import { HttpClient, HttpHeaders,  } from '@angular/common/http';
import { Injectable , Inject} from '@angular/core';
import { map, Observable, throwError } from 'rxjs';
import { AppConfig } from '../AppConfig/appconfig.interface';
import { APP_SERVICE_CONFIG } from '../AppConfig/appconfig.service';
import axios from 'axios';
import { TOsoba } from '../model/a1Request/osoba';
import { Adresa } from '../model/a1Request/adresa';
import { ObrazacA1 } from '../model/a1Request/obrazacA1';


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

  SubmitA1Request(request: ObrazacA1, descriptionFile?: File, exampleFile? : File) {
    if(descriptionFile===undefined && exampleFile===undefined){
      axios.post('/api/a1',request);
    }else{
      if(descriptionFile !==undefined){
        const formData = new FormData();
        formData.append('file',<File>descriptionFile, (<File>descriptionFile).name);
        axios.post('/api/a1/uploadDescriptionFile',formData).then((response) =>{
          //request.SetDescriptionId(response.data);
          if(exampleFile !==undefined){
            const exampleFormData = new FormData();
            exampleFormData.append('file',<File>exampleFile, (<File>exampleFile).name);
            axios.post('/api/a1/uploadExampleFile',exampleFormData).then((response) =>{
              //request.SetExampleId(response.data);
              axios.post('/api/a1',request);
            });
          }else{
            axios.post('/api/a1',request);
          }
        })
      }else{
        const exampleFormData = new FormData();
        exampleFormData.append('file',<File>exampleFile, (<File>exampleFile).name);
        axios.post('/api/a1/uploadExampleFile',exampleFormData).then((response) =>{
          //request.SetExampleId(response.data);
          axios.post('/api/a1',request);
        });
      }
    }
  }
}

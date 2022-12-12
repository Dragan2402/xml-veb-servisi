import { HttpClient, HttpHeaders,  } from '@angular/common/http';
import { Injectable , Inject} from '@angular/core';
import { map, Observable, throwError } from 'rxjs';
import { AppConfig } from '../AppConfig/appconfig.interface';
import { APP_SERVICE_CONFIG } from '../AppConfig/appconfig.service';
import axios from 'axios';
import { Person } from '../model/a1Request/person';
import { DomesticCitizenship } from '../model/a1Request/citizenship/domesticCitizenship';
import { Address } from '../model/a1Request/address';
import { A1Request } from '../model/a1Request/a1Request';


@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient) {
     }


  getExample(): Observable<any> {
    return this.http.get("/api/a1");

  }

  SubmitA1Request(request: A1Request, descriptionFile?: File, exampleFile? : File) {
    if(descriptionFile===undefined && exampleFile===undefined){
      axios.post('/api/a1',request);
    }else{
      if(descriptionFile !==undefined){
        const formData = new FormData();
        formData.append('file',<File>descriptionFile, (<File>descriptionFile).name);
        axios.post('/api/a1/uploadDescriptionFile',formData).then((response) =>{
          request.SetDescriptionId(response.data);
          if(exampleFile !==undefined){
            const exampleFormData = new FormData();
            exampleFormData.append('file',<File>exampleFile, (<File>exampleFile).name);
            axios.post('/api/a1/uploadExampleFile',exampleFormData).then((response) =>{
              request.SetExampleId(response.data);
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
          request.SetExampleId(response.data);
          axios.post('/api/a1',request);
        });
      }
    }
  }

  UploadDescriptionFile(formData: FormData) : Observable<any>{

    return this.http.post<string>('/api/a1/uploadDescriptionFile',formData, { responseType: 'text' as 'json' }).pipe(
      map((response:any) =>{
        return response;
      }
      ));
    }
}

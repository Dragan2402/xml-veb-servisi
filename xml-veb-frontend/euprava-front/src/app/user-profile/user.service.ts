import { HttpClient } from '@angular/common/http';
import { Injectable , Inject} from '@angular/core';
import { Observable, throwError } from 'rxjs';
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
}

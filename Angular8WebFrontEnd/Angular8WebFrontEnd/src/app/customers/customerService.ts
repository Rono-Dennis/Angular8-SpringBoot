import {Injectable} from '@angular/core'; 
import {Observable} from 'rxjs';    
import { HttpClient } from '@angular/common/http';
import { Customers } from './customers';

@Injectable({
    providedIn: 'root'  
})

export class customerService {
    constructor(private http: HttpClient) {}

    getEmployeesList(): Observable<Customers[]> {
        return this.http.get<Customers[]>("http://localhost:8092/springboot-rest-api/api/v1/customers/");
      }
 
}
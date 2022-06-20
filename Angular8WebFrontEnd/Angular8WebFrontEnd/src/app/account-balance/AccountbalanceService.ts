
import {Injectable} from '@angular/core'; 
import {Observable} from 'rxjs';    
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'  
})

export class AccountbalanceService {

    private baseUrl = "http://localhost:8092/springboot-rest-api/api/v1/accounts/";
    constructor(private http: HttpClient) {}

    findBalance(accountNo): Observable<Object> {
        return this.http.get(`${this.baseUrl}/${accountNo}`);
      } 
}


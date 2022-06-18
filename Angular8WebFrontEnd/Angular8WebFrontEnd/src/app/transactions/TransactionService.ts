import {Injectable} from '@angular/core'; 
import {Observable} from 'rxjs';    
import { HttpClient } from '@angular/common/http';
import { Transactions } from './Transactions';

@Injectable({
    providedIn: 'root'  
})

export class TransactionsService {
    constructor(private http: HttpClient) {}

    getEmployeesList(): Observable<Transactions[]> {
        return this.http.get<Transactions[]>("http://localhost:8092/springboot-rest-api/api/v1/transactions/");
      }
 
}
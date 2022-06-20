import {Injectable} from '@angular/core'; 
import {Observable} from 'rxjs';    
import { HttpClient } from '@angular/common/http';
import { Customers } from './customers';
import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'  
})

export class customerService {
  [x: string]: any;
    constructor(private http: HttpClient) {}
    public baseUrl = "http://localhost:8092/springboot-rest-api/api/v1/customers/transaction";

    public searchResults: any;
    getEmployeesList(){
        return this.http.get<Customers[]>(`${this.baseUrl}`);
      }

      getEmployesList(): Observable<Customers[]> {
        return this.http.get<Customers[]>(`${this.baseUrl}`);
      }

      getEmployeesListSearch(customerId: String): Observable<any> {
        return this.http.get(`${this.baseUrl}/${customerId}`);
        // localhost:8092/springboot-rest-api/api/v1/customers/CUST1002
        // return this.http.get<Customers[]>("localhost:8092/springboot-rest-api/api/v1/accounts/CUST1002");
      }


      public searchEntries(term): Observable<any>{
        if(term === ""){
        console.log("Not defined term");
      
      }else{
        let params = {q:term }
        return this.httpClient.get(this.baseUrl, {params}).pipe(
          map(response =>{
            console.log(response);
            return this.searchResults = response["items"];
          })
        );  
      }
    }

    public _searchResults(term){
      return this.searchEntries(term);
    }
  }
  


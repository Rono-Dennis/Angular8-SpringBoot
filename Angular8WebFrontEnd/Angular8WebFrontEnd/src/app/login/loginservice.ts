import {Injectable} from '@angular/core';
import { Webuser } from './Webuser';
import {Observable} from 'rxjs';    
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'  
})

export class loginService {
    constructor(private http: HttpClient) {}

    public loginUserFromRemote(webuser :Webuser): Observable<any  > {    
        return this.http.post<any>("http://localhost:8092/springboot-rest-api/api/v1/webusers/login", webuser)
    }
}
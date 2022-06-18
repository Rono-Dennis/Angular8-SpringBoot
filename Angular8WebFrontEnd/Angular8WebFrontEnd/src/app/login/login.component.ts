import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Webuser } from './Webuser';
import {loginService} from './loginservice'; 
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})  
export class LoginComponent implements OnInit {
  webuser = new Webuser();

  constructor(private _service :  loginService, private _router : Router
    ) { }

  ngOnInit() {
  }

  loginUser(){
    this._service.loginUserFromRemote(this.webuser).subscribe(
      data =>{
        console.log("response received");
      this._router.navigate(['./customers'])
      }, 
      error => console.error("response not received"),
    ) 
  }

}
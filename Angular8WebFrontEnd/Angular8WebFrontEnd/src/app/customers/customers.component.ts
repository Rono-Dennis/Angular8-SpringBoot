import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { concat, forkJoin, of, Subject, throwError } from 'rxjs';
import { Customers } from './customers';
import {customerService} from './customerService';
import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs'; 
import { catchError, debounceTime, distinctUntilChanged, map, switchMap } from 'rxjs/operators';
 

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {
   customer:   Customers[] = [];
   first_name:any;
  searchText:any;
  customers: Observable<Customers[]>;
  constructor(public customersServices: customerService, private http: HttpClient) { }

  // public zoneName$ = new Customers();\
  public searchTerm = new Subject<string>();
  public loading:boolean;
  public searchResults: any;
  public paginationElements: any;
  public errorMessage: any;


  public searchForm = new FormGroup({
    search: new FormControl("",Validators.required),
  });

  public search(){
    this.searchTerm.pipe(map((e: any) =>{
      console.log(e.target.value);
      return e.target.value;
    }),
    
    debounceTime(400),
    distinctUntilChanged(),
    switchMap(term => {
      this.loading = true;
      return this.customersServices._searchResults(term);
    }),
    catchError((e)=>{
      console.log(e);
      this.loading = false;
      this.errorMessage = e.message;
      return throwError(e);     
    }),).subscribe(v =>{
      this.loading = true;
      this.searchResults = v;
      this.paginationElements = this.searchResults;
    })
    
  }
 
  ngOnInit() {

    this.customersServices.getEmployeesList().subscribe((response) =>{
      this.customer = response;
    }) 
     
    this.customers = this.customersServices.getEmployeesList();
    let response = this.customers;
    console.log(this.customers);

   
  }
   
  Search(){
    if(this.first_name == ""){
      this.ngOnInit();
    }else{
      this.customer = this.customer.filter(res =>{

        return this.customersServices.first_name.toLocaleLowerCase().match(this.first_name.toLocaleLowerCase());
      })
    }
  }

}
 


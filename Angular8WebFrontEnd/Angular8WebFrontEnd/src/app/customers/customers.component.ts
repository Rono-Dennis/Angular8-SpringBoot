import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Customers } from './customers';
import {customerService} from './customerService';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {
  customers: Observable<Customers[]>;
  constructor(private customersServices: customerService) { }

  ngOnInit() {
    this.customers = this.customersServices.getEmployeesList();
    let response = this.customers;
    console.log(this.customers);

   
  }
   

}

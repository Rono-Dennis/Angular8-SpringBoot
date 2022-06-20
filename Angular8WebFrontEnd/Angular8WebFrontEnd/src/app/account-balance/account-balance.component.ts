import { Component, Injectable, OnInit } from '@angular/core';
import { Transactions } from './Transactions';
import { AccountbalanceService} from './AccountbalanceService';
import { Router, ActivatedRoute } from '@angular/router';

@Injectable({
  providedIn: 'root'  
})

@Component({
  selector: 'app-account-balance',
  templateUrl: './account-balance.component.html',
  styleUrls: ['./account-balance.component.scss']
})
export class AccountBalanceComponent implements OnInit {
  accountNo: String;
  transactions: Transactions;

  constructor(private route: ActivatedRoute, private router: Router, private accountService: AccountbalanceService) { }

  ngOnInit() {
    // this.transactions = new Transactions();
    // this.accountNo = this.route.snapshot.params["accountNo"];
    // this.accountNo = this.accountService.getAccountNo();
  }

 public updateEmployee(transactions: Transactions) {
    this.accountService.findBalance(transactions)
      .subscribe((response: Transactions) => {
        console.log(response); 
        // this.transactions = new Transactions();
        this.gotoList();
      }, error => console.log(error));
  }

  gotoList() {
    // this.accountNo;
    this.transactions = new Transactions();
    // this.router.navigate(['/employees']);
  }

}

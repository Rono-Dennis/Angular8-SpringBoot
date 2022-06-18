import { Component, OnInit } from '@angular/core';
import { Transactions } from './Transactions';
import { AccountbalanceService} from './AccountbalanceService';

@Component({
  selector: 'app-account-balance',
  templateUrl: './account-balance.component.html',
  styleUrls: ['./account-balance.component.scss']
})
export class AccountBalanceComponent implements OnInit {
  id: number;
  transactions: Transactions;

  constructor(private accountService: AccountbalanceService) { }

  ngOnInit() {
    this.transactions = new Transactions();
  }

}

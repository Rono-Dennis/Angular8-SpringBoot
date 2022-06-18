import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { Transactions } from "./Transactions";
import { TransactionsService } from "./TransactionService";

@Component({
  selector: "app-transactions",
  templateUrl: "./transactions.component.html",
  styleUrls: ["./transactions.component.scss"],
})
export class TransactionsComponent implements OnInit {
  transactions: Observable<Transactions[]>;
  constructor(private transactionsService: TransactionsService) {}

  ngOnInit() {
    this.transactions = this.transactionsService.getEmployeesList();
    let response = this.transactions;
    console.log(this.transactions);
  }
}

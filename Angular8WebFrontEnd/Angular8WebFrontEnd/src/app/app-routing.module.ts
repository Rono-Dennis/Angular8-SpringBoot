import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { TransactionsComponent } from "./transactions/transactions.component";
import { LoginComponent } from "./login/login.component";
import { PageNotFoundComponent } from "./page-not-found/page-not-found.component";
import { CustomersComponent } from "./customers/customers.component";
import { AccountBalanceComponent } from "./account-balance/account-balance.component";

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "transactions", component: TransactionsComponent },
  { path: "customers", component: CustomersComponent },
  { path: "account-balance", component: AccountBalanceComponent },
  { path: "", redirectTo: "/login", pathMatch: "full" },
  { path: "**", component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { enableTracing: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}

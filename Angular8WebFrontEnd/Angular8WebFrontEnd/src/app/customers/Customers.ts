export class Customers {
  id: number;
  customer_id: string;
  email: string; 
  first_name: string;
  last_name: string;
  account_no: string;
  username: string;

  constructor(id, email,customer_Id,first_name,last_name,account_no,username){
    this.id = id;
    this.customer_id = customer_Id;
    this.email = email;
    this.first_name = first_name;
    this.last_name = last_name;
    this.account_no = account_no;
    this.username = username;
  }
}
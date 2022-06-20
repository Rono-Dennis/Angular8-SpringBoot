package com.comulynx.wallet.rest.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accountcustomer")


@SqlResultSetMapping(
        name = "transactionMapping",
        classes = {
                @ConstructorResult(
                        targetClass = AccountCustomer.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "first_name"),
                                @ColumnResult(name = "last_name"),
                                @ColumnResult(name = "email"),
                                @ColumnResult(name = "customer_id"),
                                @ColumnResult(name = "account_no"),
                                @ColumnResult(name = "balance",type = double.class)
                        })
        }
)
@NamedNativeQuery(name = "AccountCustomer.findAllAccountCustomer",
        query = "SELECT s.ID, s.FIRST_NAME, s.LAST_NAME, " +
                "s.EMAIL, s.CUSTOMER_ID, n.ACCOUNT_NO, " +
                "n.BALANCE FROM Customers s INNER  join  Accounts n  " +
                "on s.CUSTOMER_ID = n.CUSTOMER_ID",
        resultSetMapping = "transactionMapping",
        resultClass = AccountCustomer.class
)
public class AccountCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String first_name;
    private String last_name;
    private String email;
    @Column(unique = true,nullable = false)
    private String customer_id;
    @Column(unique = true,nullable = false)
    private String account_no;
    @Column(unique = true,nullable = false)
    private double balance;

}

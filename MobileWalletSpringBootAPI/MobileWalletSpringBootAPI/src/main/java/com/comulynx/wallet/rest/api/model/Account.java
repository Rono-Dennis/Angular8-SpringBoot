package com.comulynx.wallet.rest.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "ACCOUNT_NO")
	private long id;
    @Column(unique = true,nullable = false)
    private String accountNo;
	@Column(unique = true,nullable = false)
	private String customerId;
	@Column(nullable = false)
	private double balance;


}

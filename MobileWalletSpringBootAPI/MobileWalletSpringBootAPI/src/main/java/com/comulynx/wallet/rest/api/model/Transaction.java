package com.comulynx.wallet.rest.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    @Column(unique = true,nullable = false)
    private String transactionId;
	@Column(nullable = false)
	private String customerId;
	@Column(nullable = false)
	private String accountNo;
	@Column(nullable = false)
	private double amount;
	@Column(nullable = false)
	private double balance;
	@Column(nullable = false)
	private String transactionType;
	@Column(nullable = false)
	private String debitOrCredit;
	

}

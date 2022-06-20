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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "webusers")
public class Webuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    @Column(unique = true,nullable = false)
    private String username;
    private String password;
	private String firstName;
	private String lastName;
	@Column(unique = true,nullable = false)
	private String email;
	@Column(unique = true,nullable = false)
	private String employeeId;
	@Column(unique = true,nullable = false)
	private String customerId;


}

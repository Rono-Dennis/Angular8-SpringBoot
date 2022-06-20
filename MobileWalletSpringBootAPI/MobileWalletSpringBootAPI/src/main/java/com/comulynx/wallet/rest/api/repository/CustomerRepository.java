package com.comulynx.wallet.rest.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.comulynx.wallet.rest.api.model.AccountCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerId(String customerId);

	Optional<Customer> findByCustomerIdAndPin(String customerId, String pin);

	// TODO : Implement the query and function below to delete a customer using Customer Id
	@Modifying
	 @Query("delete from Customer f where f.customerId =?1")
	 int deleteCustomerByCustomerId(String customer_id);

	// TODO : Implement the query and function below to update customer firstName using Customer Id
	 @Query("update Customer u set u.firstName = ?1 where u.customerId =?2")
	 int updateCustomerByCustomerId(String firstName, String customer_id);
	
	// TODO : Implement the query and function below and to return all customers whose Email contains  'gmail'
	 @Query("SELECT u FROM Customer u WHERE u.email LIKE '%gmail%'")
	 List<Customer> findAllCustomersWhoseEmailContainsGmail();

//	@Query("SELECT email,SUBSTRING_INDEX(email,'@',1) AS user_name,SUBSTRING_INDEX(email,'@',-1) AS domain_name FROM email_table")



}

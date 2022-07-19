package com.comulynx.wallet.rest.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Optional<List<Transaction>> findByCustomerId(String customerId);

	Optional<List<Transaction>> findTransactionsByTransactionId(String transactionId);


	Optional<List<Transaction>> findTransactionsByCustomerIdAndTransactionId(String transactionId, String customerId);



//	Optional<List<Transaction>> findTransactionsByCustomerId(String customerId);

//	@Query("SELECT t FROM Transaction t WHERE t.customerId =?1 AND  t.accountNo =?2 ORDER BY accountNo desc limit 1")
//	@Query(nativeQuery = true, value = "SELECT t FROM Transaction t WHERE t.customerId =?1 AND  t.accountNo =?2 ORDER BY accountNo DESC LIMIT 5")
//	List<Transaction> getMiniStatementCustomerIdAndAccountNo(String customer_id, String account_no);
	// TODO : Change below Query to use Named Parameters instead of indexed
	// parameters

	// TODO : Change below function to return Optional<List<Transaction>>

	/*using named parameter*/
	@Query("SELECT t FROM Transaction t WHERE t.customerId = :customerId AND  t.accountNo = :accountNo")
	List<Transaction> getMiniStatementCustomerIdAndAccountNo(@Param("customerId") String customer_id, @Param("accountNo") String account_no);

	/*using indexed parameters*/
	/*// TODO : Change below function to return Optional<List<Transaction>>
	@Query("SELECT t FROM Transaction t WHERE t.customerId =?1 AND  t.accountNo =?2")
	List<Transaction> getMiniStatementCustomerIdAndAccountNo(String customer_id, String account_no);*/

	// TODO : Change below Query to return the last 5 transactions
	/*//get data from database pageable getting last 5 transactions*/
	@Query("select p from Transaction p")
	List<Transaction> findWithPageable(Pageable pageable);

	//get data from database pageable
	@Query(value="SELECT t FROM Transaction t WHERE t.customerId =?1 AND  t.accountNo =?2  LIMIT 5", nativeQuery = true)
	List<Transaction> getMiniStatementUsingCustomerIdAndAccountNo(String customer_id, String account_no);


}

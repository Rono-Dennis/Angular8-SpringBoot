package com.comulynx.wallet.rest.api.repository;

import com.comulynx.wallet.rest.api.model.AccountCustomer;
import com.comulynx.wallet.rest.api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@NoRepositoryBean
//@Repository

//@EnableJpaRepositories(basePackageClasses = {RepoPackageMarker.class, FilterRepositoryCustomImpl.class})
public interface CustomerAccountRepository extends JpaRepository<AccountCustomer, Long> {

//    @Query("SELECT Customers.FIRST_NAME, Customers.LAST_NAME, Customers.EMAIL, Customers.CUSTOMER_ID, Accounts.ACCOUNT_NO, Accounts.balance FROM Customers INNER  join  Accounts  on Customers.customer_Id = Accounts.customer_Id")
//@Query(nativeQuery = true)
//List<AccountCustomer> findAllTransactions();

    List<AccountCustomer> findAllAccountCustomer();

//    Optional findByCustomerId(String customerId);
}

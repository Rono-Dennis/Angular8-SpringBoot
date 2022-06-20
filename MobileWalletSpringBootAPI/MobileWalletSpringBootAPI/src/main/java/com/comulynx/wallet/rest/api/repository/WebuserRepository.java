package com.comulynx.wallet.rest.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Webuser;

@Repository
public interface WebuserRepository extends JpaRepository<Webuser, Long>{
	
	Optional<Webuser> findByEmployeeId(String employeeId);


  Webuser findByEmployeeIdAndPassword(String employee_id, String password);

//    Webuser findByUserNameAndEmail(String username, String email);

    Webuser findByEmployeeIdAndCustomerId(String employeeId, String customerId);
}

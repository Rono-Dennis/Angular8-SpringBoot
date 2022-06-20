package com.comulynx.wallet.rest.api.controller;

import java.security.SecureRandom;
import java.util.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.validation.Valid;

import com.comulynx.wallet.rest.api.model.AccountCustomer;
import com.comulynx.wallet.rest.api.repository.CustomerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comulynx.wallet.rest.api.exception.ResourceNotFoundException;
import com.comulynx.wallet.rest.api.model.Account;
import com.comulynx.wallet.rest.api.model.Customer;
import com.comulynx.wallet.rest.api.repository.AccountRepository;
import com.comulynx.wallet.rest.api.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerAccountRepository customerAccountRepository;

	@Autowired
	private AccountRepository accountRepository;

	/**
	 * 
	 * Login
	 * 
	 * @param
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> customerLogin(@RequestBody Customer customer) {

		try {

			return ResponseEntity.status(200).body(HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}


	@GetMapping("/transaction")
	public ResponseEntity<List<AccountCustomer>>getAllTransaction(){
		return ResponseEntity.ok(customerAccountRepository.findAllAccountCustomer());
	}

	@GetMapping("/")
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable(value = "customerId") String customerId)
			throws Throwable {
		Customer customer =  customerRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		return ResponseEntity.ok().body(customer);
	}

	@PostMapping("/")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer) {
		try {

			/** hashing pin*/

			String hashPin = customer.getPin();

			SecureRandom secureRandom = new SecureRandom();
			byte[] salt = secureRandom.generateSeed(12);

			PBEKeySpec pbeKeySpec = new PBEKeySpec(hashPin.toCharArray(), salt, 10, 512);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			byte[] hash = skf.generateSecret(pbeKeySpec).getEncoded();

			/* pin hashed */
			String base64Hash = Base64.getMimeEncoder().encodeToString(hash);

			/**Checking if username exist*/

			Optional<Customer> customerId = Optional.ofNullable(customerRepository.
					findByCustomerId(customer.getCustomerId())
					.orElseThrow(() -> new ResourceNotFoundException("No such user with customerID" + customer.getCustomerId())));


			 customerId.orElseThrow(() -> new ResourceNotFoundException("user of " + customerId+ "exist"));


			// TODO : Add logic to Hash Customer PIN here
			// TODO : Add logic to check if Customer with provided username, or
			// customerId exists. If exists, throw a Customer with [?] exists
			// Exception.

			String accountNo = generateAccountNo(customer.getCustomerId());
			Account account = new Account();
			account.setCustomerId(customer.getCustomerId());
			account.setAccountNo(accountNo);
			account.setBalance(0.0);
			accountRepository.save(account);

			return ResponseEntity.ok().body(customerRepository.save(customer));
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "customerId") String customerId,
			@Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
		Customer customer = customerRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

		customer.setEmail(customerDetails.getEmail());
		customer.setLastName(customerDetails.getLastName());
		customer.setFirstName(customerDetails.getFirstName());
		final Customer updatedCustomer = customerRepository.save(customer);
		return ResponseEntity.ok(updatedCustomer);
	}

//	@PostMapping("/{customerId}")
//	public ResponseEntity<AccountCustomer> customerUpdate(@PathVariable(value = "customerId") String customerId,) throws ResourceNotFoundException){
//

//	}

	/***find all customers with 'gmail' */
	@GetMapping("/gmail")
	public ResponseEntity<List<Customer>>getAllCustomerWithGmail(){
		return ResponseEntity.ok(customerRepository.findAllCustomersWhoseEmailContainsGmail());
	}

	/**delete from repository query
	 *
	 * The code is commented since it throws illegalStateException
	 * Ambiguous mapping
	 * methods=[DELETE]}: There is already 'customerController' bean method
	 * @return*/

	/** i therefore comment this code to allow me proceed to run*/


	/**@DeleteMapping("/{customerId}")
	public ResponseEntity<Integer> deletingCustomer(@PathVariable(value = "customerId") String customerId) throws  Exception{
		int customer = customerRepository.deleteCustomerByCustomerId(customerId);
		return ResponseEntity.ok(customer);
	}*/

	@DeleteMapping("/{customerId}")
	public Map<String, Boolean> deleteCustomer(@PathVariable(value = "customerId") String customerId)
			throws ResourceNotFoundException {
		Customer customer = customerRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

		customerRepository.delete(customer);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}



	/**
	 * generate a random but unique Account No (NB: Account No should be unique
	 * in your accounts table)
	 * 
	 */
	private String generateAccountNo(String customerId) {

//		UUID uuid = UUID.randomUUID();
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
				+"lmnopqrstuvwxyz!@#$%&";

		Random random = new Random();
		StringBuilder builder = new StringBuilder(customerId);
		for (int i = 0; i < customerId.length(); i++)
			builder.append(chars.charAt(random.nextInt(chars.length())));
		return builder.toString();

		/**done
		* */

		// TODO : Add logic here - generate a random but unique Account No (NB:
		// Account No should be unique in the accounts table)

	}
}

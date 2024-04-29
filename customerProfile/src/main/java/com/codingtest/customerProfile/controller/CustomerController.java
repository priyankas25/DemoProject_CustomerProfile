package com.codingtest.customerProfile.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codingtest.customerProfile.model.CustomerModel;
import com.codingtest.customerProfile.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins ="*",allowedHeaders="*")
@Validated
public class CustomerController {
	
	private static Logger logger  = LogManager.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService cservice;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CustomerModel>>getCustomerDetails() throws Exception{
		List<CustomerModel> info = cservice.getAllCustomers();
		return new ResponseEntity<List<CustomerModel>>(info, HttpStatus.OK);
	}
	
	@PostMapping("/saveCustomer")
	public ResponseEntity<Boolean> SaveCustomer(@Valid @RequestBody CustomerModel customerModel) throws MethodArgumentNotValidException {
		Boolean status = cservice.saveCustomer(customerModel);
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}
	
	@PutMapping("/updateCustomer")
	public Boolean updateCustomer(@RequestBody CustomerModel customerModel) throws Exception {
		Boolean status = cservice.updateCustomer(customerModel);
		return status;
	}
	
	@GetMapping("/deactivateCustomer/{id}")
	public void deactivate(@PathVariable("id") int id) throws Exception {
		cservice.DeactivateCustomer(id);
		logger.info("done");
	}
	
	@GetMapping("/searchByName/{name}")
	public ResponseEntity<CustomerModel>searchCustomer(@PathVariable("name") String name) throws Exception{
		CustomerModel info = cservice.searchCustomer(name);
		logger.info("done",info);
		return new ResponseEntity<CustomerModel>(info,HttpStatus.OK);
		
	}
}

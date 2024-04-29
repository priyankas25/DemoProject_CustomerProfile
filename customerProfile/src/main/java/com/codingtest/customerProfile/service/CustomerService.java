package com.codingtest.customerProfile.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingtest.customerProfile.controller.CustomerController;
import com.codingtest.customerProfile.model.CustomerModel;
import com.codingtest.customerProfile.repository.CustomerRepo;

@Service
public class CustomerService {
	private static Logger logger  = LogManager.getLogger(CustomerController.class);
	
	@Autowired
	CustomerRepo repo;
	
	public List<CustomerModel> getAllCustomers() {
		return repo.findAll();
	}
	
	public Boolean saveCustomer(CustomerModel model) {
		CustomerModel returnmodel= repo.save(model);
		Optional<CustomerModel> savedmodel= Optional.ofNullable(returnmodel);
			if(savedmodel.isPresent()) {
				return true;
			}else {
				return false;
			}
	}
	
	public Boolean updateCustomer(CustomerModel model) {
		CustomerModel returnmodel= repo.save(model);
		Optional<CustomerModel> savedmodel= Optional.ofNullable(returnmodel);
			if(savedmodel.isPresent()) {
				return true;
			}else {
				return false;
			}
	}
	
	public void DeactivateCustomer(int id) {
		repo.deactivateCustomer(id);
	}
	
	public CustomerModel searchCustomer(String name) {
		return repo.searchByName(name);
	}
}

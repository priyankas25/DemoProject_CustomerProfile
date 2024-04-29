package com.codingtest.customerProfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codingtest.customerProfile.model.CustomerModel;

import jakarta.transaction.Transactional;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerModel, Integer> {
	
		
		@Modifying
		@Transactional
		@Query(value="update Customer c set c.cust_isActive='false' where c.cust_Id=:id",nativeQuery=true)
		public void deactivateCustomer(int id);
		
		@Query(value="select * from Customer C where C.cust_FName like %:name% or C.cust_LName like %:name% or CONCAT(C.cust_FName,' ',C.cust_LName)=%:name%",nativeQuery=true)
		public CustomerModel searchByName(String name);
}

package com.codingtest.customerProfile.model;

import java.util.Date;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor

@Table(name="Customer")
public class CustomerModel {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	public int cust_Id;
	
	public String cust_FName;
	
	public String cust_LName;
	
	
	@NotBlank
	@Pattern(regexp="[A-Za-z0-9\\._%+\\-]+@[A-Za-z0-9\\.\\-]+\\.[A-Za-z]{2,}",message="Email not in valid format. format should be like kangoedin@gmail.com")
	@Column(unique = true)
	public String cust_Email;
	
	@JsonFormat(pattern="dd-mm-yyyy")
	public Date cust_dateOfBirth;
	
	@JsonFormat(pattern="dd-mm-yyyy")
	public Date cust_registrationDate;
	
	public boolean cust_isActive;
}

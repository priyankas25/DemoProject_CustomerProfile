package com.codingtest.customerProfile;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
class CustomerProfileApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;

	
	@Test
	void getCustomerDetails_test() throws Exception {
		this.mockMvc.perform(get("/getAll"))
        .andExpect(status().isOk())
        .andDo(document("Get the Customers"));
	}
	
	@Test
	void SaveCustomer_test() throws Exception {
		
		 String requestBody = "{\"cust_FName\": \"priyanka\", \"cust_LName\": \"soni\", \"cust_Email\": \"user@example.com\",\"cust_dateOfBirth\": \"29-04-1994\",\"cust_registrationDate\":\"27-04-2024\",\"cust_isActive\":true}";

	        this.mockMvc.perform(post("/saveCustomer")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(requestBody))
	                .andExpect(status().isOk())
	                .andDo(document("customer-save",
	                        requestFields(
	                                fieldWithPath("cust_FName").type(JsonFieldType.STRING).description("first name"),
	                                fieldWithPath("cust_LName").type(JsonFieldType.STRING).description("last name"),
	                                fieldWithPath("cust_Email").type(JsonFieldType.STRING).description("email"),
	                                fieldWithPath("cust_dateOfBirth").type(JsonFieldType.STRING).description("date of birth"),
	                                fieldWithPath("cust_registrationDate").type(JsonFieldType.STRING).description("registration date"),
	                                fieldWithPath("cust_isActive").type(JsonFieldType.BOOLEAN).description("Indicates if the Customer is active")
	                        )
	                ));
        
    }
	
	@Test
	void UpdateCustomer_test() throws Exception {
		
		 String requestBody = "{\"cust_Id\":\"1\",\"cust_FName\": \"priyanka\", \"cust_LName\": \"soni\", \"cust_Email\": \"user@example.com\",\"cust_dateOfBirth\": \"29-04-1994\",\"cust_registrationDate\":\"27-04-2024\",\"cust_isActive\":true}";

	        this.mockMvc.perform(post("/updateCustomer/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(requestBody))
	                .andExpect(status().isOk())
	                .andDo(document("customer-save",
	                        requestFields(
	                        		fieldWithPath("cust_Id").type(JsonFieldType.NUMBER).description("first name"),
	                                fieldWithPath("cust_FName").type(JsonFieldType.STRING).description("first name"),
	                                fieldWithPath("cust_LName").type(JsonFieldType.STRING).description("last name"),
	                                fieldWithPath("cust_Email").type(JsonFieldType.STRING).description("email"),
	                                fieldWithPath("cust_dateOfBirth").type(JsonFieldType.STRING).description("date of birth"),
	                                fieldWithPath("cust_registrationDate").type(JsonFieldType.STRING).description("registration date"),
	                                fieldWithPath("cust_isActive").type(JsonFieldType.BOOLEAN).description("Indicates if the Customer is active")
	                        )
	                ));
        
    }
	
	@Test
	void deactivateCustomer_test() throws Exception {
		this.mockMvc.perform(get("/deactivateCustomer/1"))
        .andExpect(status().isNoContent())
        .andDo(document("Deactivate Customer"
        			
        		));
	}
	
	@Test
	void searchByName_test() throws Exception {
		this.mockMvc.perform(get("/searchByName/priyanka"))
        .andExpect(status().isOk())
        .andDo(document("Customer search by name",
        		responseFields(
        				fieldWithPath("cust_Id").type(JsonFieldType.NUMBER).description("first name"),
                        fieldWithPath("cust_FName").type(JsonFieldType.STRING).description("first name"),
                        fieldWithPath("cust_LName").type(JsonFieldType.STRING).description("last name"),
                        fieldWithPath("cust_Email").type(JsonFieldType.STRING).description("email"),
                        fieldWithPath("cust_dateOfBirth").type(JsonFieldType.STRING).description("date of birth"),
                        fieldWithPath("cust_registrationDate").type(JsonFieldType.STRING).description("registration date"),
                        fieldWithPath("cust_isActive").type(JsonFieldType.BOOLEAN).description("Indicates if the Customer is active")
                )
        		));
	}
}



package com.example.mstransaction.models.entities;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purse {
    
    @Id
	private String id;
	
	private String typeIdentification;
	
	private String identification;
	
	private String phone;
	
	private String imei;
	
	private String email;
	
	private String cardNumber;

	private Double amount;
	

}

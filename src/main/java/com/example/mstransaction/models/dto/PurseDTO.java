package com.example.mstransaction.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurseDTO {
 
	private String phoneTransmitter;

    private String phoneReceiver;
    
    private Double amount;

    private String type;
	

}

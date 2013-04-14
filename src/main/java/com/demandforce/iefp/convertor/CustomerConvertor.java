package com.demandforce.iefp.convertor;

import com.demandforce.iefp.dto.CustomerDTO;
import com.demandforce.iefp.model.Customer;

public class CustomerConvertor {

	private CustomerConvertor() {
		
	}
	
	public static CustomerDTO modelToDTO(Customer customer) {
		CustomerDTO dto = new CustomerDTO();
		//TODO : convert...
		
		return dto;
	}
}

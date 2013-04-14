package com.demandforce.iefp.matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.demandforce.iefp.dto.CustomerDTO;
import com.demandforce.iefp.model.Customer;
import com.demandforce.iefp.model.MatchResult;

/**
 * This is the strict match ruler.
 * FirstName, LastName, Address, Zipcode, State, City 
 * @author bobby
 *
 */
@Component
public class DefaultMatchRuler extends MatchRuler {
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMatchRuler.class);
	public DefaultMatchRuler() {
		setPriority(Integer.MAX_VALUE);
	}
	@Override
	public MatchResult matches(CustomerDTO customerDto) {
		// TODO Need verify the customer fields here?
		Customer customer = new Customer();
		
		return null;
	}

}

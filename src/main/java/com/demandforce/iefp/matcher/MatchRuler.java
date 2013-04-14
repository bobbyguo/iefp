package com.demandforce.iefp.matcher;

import com.demandforce.iefp.dto.CustomerDTO;
import com.demandforce.iefp.model.MatchResult;

/**
 * Match Ruler used to match the customer from our db.
 * @author bobby
 *
 */
public abstract class MatchRuler implements Comparable<MatchRuler> {

	private int priority; //the priority of the rule,the bigger the higher priority.
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getPriority() {
		return priority;
	}
	public int compareTo(MatchRuler o) {
		
		return o.priority - this.priority;
	}
	
	public abstract MatchResult matches(CustomerDTO customerDto);
	
}

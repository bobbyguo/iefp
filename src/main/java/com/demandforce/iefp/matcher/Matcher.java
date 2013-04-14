package com.demandforce.iefp.matcher;

import com.demandforce.iefp.dto.CustomerDTO;
import com.demandforce.iefp.model.MatchResult;

public interface Matcher {
	boolean isMatch(final CustomerDTO customerDto);
	MatchResult match(final CustomerDTO customerDto);
}

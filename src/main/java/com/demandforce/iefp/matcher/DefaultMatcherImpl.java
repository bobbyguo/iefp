package com.demandforce.iefp.matcher;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.demandforce.iefp.dto.CustomerDTO;
import com.demandforce.iefp.model.MatchResult;

@Component
public class DefaultMatcherImpl implements Matcher {

	@Resource
	private List<MatchRuler> matcherRulerChain;

	@PostConstruct
	public void init() {
		Collections.sort(matcherRulerChain);
	}

	public boolean isMatch(CustomerDTO customerDto) {

		return match(customerDto) == null ? false : true;
	}

	public MatchResult match(CustomerDTO customerDto) {
		MatchResult result = null;
		for (MatchRuler ruler : matcherRulerChain) {
			result = ruler.matches(customerDto);
			if (result != null) {
				return result;
			}
		}
		return null;
	}
	
	

}

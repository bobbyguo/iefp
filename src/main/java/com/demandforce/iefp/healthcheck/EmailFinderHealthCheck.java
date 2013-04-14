package com.demandforce.iefp.healthcheck;

import org.springframework.stereotype.Component;

import com.yammer.metrics.core.HealthCheck;

@Component
public class EmailFinderHealthCheck extends HealthCheck{

	public EmailFinderHealthCheck() {
		this("emailFinderHealthCheck");
	}
	
	public EmailFinderHealthCheck(String name) {
		super(name);
	}

	@Override
	protected Result check() throws Exception {
		return Result.healthy();
	}

}

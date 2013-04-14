package com.demandforce.iefp.validation.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.demandforce.iefp.util.common.ResponseUtil;
import com.google.common.collect.ImmutableList;
import com.yammer.dropwizard.validation.Validator;

@Component
public class JsonValidator {

	private static final Logger LOG = LoggerFactory.getLogger(JsonValidator.class);

	private final transient Validator validator = new Validator();

	public void validate(Object o) {
		LOG.debug("validate()");
		// validate the json object passed in
		ImmutableList<String> errorMessages = validator.validate(o);
		for (String errorMessage : errorMessages) {
			LOG.debug("Validation failure : " + errorMessage);
			ResponseUtil.buildBadRequestErrorResponse(errorMessage);
		}
	}
}

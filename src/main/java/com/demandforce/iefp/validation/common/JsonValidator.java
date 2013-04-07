package com.demandforce.api.validation.common;

import static com.demandforce.api.util.common.ResponseUtil.buildBadRequestErrorResponse;

import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;
import com.yammer.dropwizard.logging.Log;
import com.yammer.dropwizard.validation.Validator;

@Component
public class JsonValidator {

	private static final Log LOG = Log.forClass(JsonValidator.class);

	private final transient Validator validator = new Validator();

	public void validate(Object o) {
		LOG.debug("validate()");
		// validate the json object passed in
		ImmutableList<String> errorMessages = validator.validate(o);
		for (String errorMessage : errorMessages) {
			LOG.debug("Validation failure : " + errorMessage);
			buildBadRequestErrorResponse(errorMessage);
		}
	}
}

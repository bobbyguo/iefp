package com.demandforce.iefp.spring;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

@SuppressWarnings({ "PMD.BeanMembersShouldSerialize" })
public class SpringServiceConfiguration extends Configuration {

	@NotNull
	@JsonProperty
	private SpringConfiguration spring;

	public SpringConfiguration getSpring() {
		return this.spring;
	}
}

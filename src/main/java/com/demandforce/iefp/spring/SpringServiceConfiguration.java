package com.demandforce.iefp.spring;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

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

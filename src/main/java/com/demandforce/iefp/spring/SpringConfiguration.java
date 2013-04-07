package com.demandforce.iefp.spring;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

@SuppressWarnings({ "PMD.BeanMembersShouldSerialize" })
public class SpringConfiguration extends Configuration {

	@NotEmpty
	@JsonProperty
	private String appContextType;

	@NotEmpty
	@JsonProperty
	private List<String> springProfiles;

	@NotEmpty
	@JsonProperty
	private List<String> configLocations;

	@NotEmpty
	@JsonProperty
	private List<String> resources;

	@JsonProperty
	private List<String> healthChecks;

	@NotEmpty
	@JsonProperty
	private String jndiContextFactory;

	@JsonProperty
	private String jndiProviderUrl;

	@JsonProperty
	private String apiContextLogFile;

	@JsonProperty
	private List<String> jerseyProviders;

	@JsonProperty
	private List<String> managed;

	@JsonProperty
	private List<String> lifeCycles;

	@JsonProperty
	private List<String> tasks;

	@JsonProperty
	private List<String> disabledJerseyFeatures;

	@JsonProperty
	private List<String> enabledJerseyFeatures;

	public String getAppContextType() {
		return appContextType;
	}

	public List<String> getConfigLocations() {
		return configLocations;
	}

	public List<String> getResources() {
		return resources;
	}

	public List<String> getHealthChecks() {
		return healthChecks;
	}

	public List<String> getJerseyProviders() {
		return jerseyProviders;
	}

	public List<String> getManaged() {
		return managed;
	}

	public List<String> getLifeCycles() {
		return lifeCycles;
	}

	public List<String> getDisabledJerseyFeatures() {
		return disabledJerseyFeatures;
	}

	public List<String> getEnabledJerseyFeatures() {
		return enabledJerseyFeatures;
	}

	public List<String> getTasks() {
		return tasks;
	}

	public List<String> getSpringProfiles() {
		return springProfiles;
	}

	public String getJndiContextFactory() {
		return jndiContextFactory;
	}

	public void setJndiContextFactory(String jndiContextFactory) {
		this.jndiContextFactory = jndiContextFactory;
	}

	public String getJndiProviderUrl() {
		return jndiProviderUrl;
	}

	public void setJndiProviderUrl(String jndiProviderUrl) {
		this.jndiProviderUrl = jndiProviderUrl;
	}

	public String getApiContextLogFile() {
		return apiContextLogFile;
	}

	public void setApiContextLogFile(String apiContextLogFile) {
		this.apiContextLogFile = apiContextLogFile;
	}
}

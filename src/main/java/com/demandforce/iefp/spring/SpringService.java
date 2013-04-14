package com.demandforce.iefp.spring;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.eclipse.jetty.util.component.LifeCycle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.demandforce.iefp.auth.BasicAuthenticator;
import com.demandforce.iefp.model.User;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.auth.basic.BasicAuthProvider;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.lifecycle.Managed;
import com.yammer.dropwizard.tasks.Task;
import com.yammer.metrics.core.HealthCheck;

@SuppressWarnings({ "PMD.TooManyMethods" })
public class SpringService extends Service<SpringServiceConfiguration> {

	private static final String INIT_CONTEXT_FILE = "dropwizardSpringContext.xml";


	private void loadResourceBeans(List<String> resources, ApplicationContext ctx, Environment env) {
		for (String resource : resources) {
			env.addResource(ctx.getBean(resource));
		}
	}

	private void loadHealthCheckBeans(List<String> healthChecks, ApplicationContext ctx, Environment env) {
		if (healthChecks != null) {
			for (String healthCheck : healthChecks) {
				env.addHealthCheck((HealthCheck) ctx.getBean(healthCheck));
			}
		}
	}

	private void loadManagedBeans(List<String> manageds, ApplicationContext ctx, Environment env) {
		if (manageds != null) {
			for (String managed : manageds) {
				env.manage((Managed) ctx.getBean(managed));
			}
		}
	}

	private void loadLifeCycleBeans(List<String> lifeCycles, ApplicationContext ctx, Environment env) {
		if (lifeCycles != null) {
			for (String lifeCycle : lifeCycles) {
				env.manage((LifeCycle) ctx.getBean(lifeCycle));
			}
		}
	}

	private void loadJerseyProviders(List<String> providers, ApplicationContext ctx, Environment env) {
		if (providers != null) {
			for (String provider : providers) {
				env.addProvider(ctx.getBean(provider));
			}
		}
//		env.addProvider(new BasicAuthProvider<User>(new BasicAuthenticator(),
//                "SUPER SECRET STUFF"));
	}

	private void loadTasks(List<String> tasks, ApplicationContext ctx, Environment env) {
		if (tasks != null) {
			for (String task : tasks) {
				env.addTask((Task) ctx.getBean(task));
			}
		}
	}

	private void enableJerseyFeatures(List<String> features, Environment env) {
		if (features != null) {
			for (String feature : features) {
				env.enableJerseyFeature(feature);
			}
		}
	}

	private void disableJerseyFeatures(List<String> features, Environment env) {
		if (features != null) {
			for (String feature : features) {
				env.disableJerseyFeature(feature);
			}
		}
	}

	private ApplicationContext initSpringParent() {
		return new ClassPathXmlApplicationContext(new String[] { INIT_CONTEXT_FILE }, true);
	}

	private ApplicationContext initSpring(SpringConfiguration config, ApplicationContext parent) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		if (config.getSpringProfiles() != null && config.getSpringProfiles().size() > 0)
			ctx.getEnvironment().setActiveProfiles(
					config.getSpringProfiles().toArray(new String[config.getSpringProfiles().size()]));
		String[] configLocations = config.getConfigLocations().toArray(new String[config.getConfigLocations().size()]);

		ctx.load(configLocations);
		ctx.refresh();
		return ctx;
	}

	public static void main(String[] args) throws Exception {
		new SpringService().run(args);
	}

	@Override
	public void initialize(Bootstrap<SpringServiceConfiguration> bootstrap) {
	}

	@Override
	public void run(SpringServiceConfiguration configuration, Environment environment)
			throws Exception {
		SpringConfiguration config = configuration.getSpring();
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, config.getJndiContextFactory());
		System.setProperty(Context.PROVIDER_URL, config.getJndiProviderUrl());
		try {
			new InitialContext();
		} catch (NamingException e) {
			throw new RuntimeException("Unable to provision Jndi", e);
		}
		ApplicationContext parentCtx = this.initSpringParent();

		Dropwizard dw = (Dropwizard) parentCtx.getBean("dropwizard");
		dw.setConfiguration(configuration);
		dw.setEnvironment(environment);

		ApplicationContext appCtx = initSpring(config, parentCtx);

		loadResourceBeans(config.getResources(), appCtx, environment);
		loadHealthCheckBeans(config.getHealthChecks(), appCtx, environment);
		loadManagedBeans(config.getManaged(), appCtx, environment);
		loadLifeCycleBeans(config.getLifeCycles(), appCtx, environment);
		loadJerseyProviders(config.getJerseyProviders(), appCtx, environment);
		loadTasks(config.getTasks(), appCtx, environment);

		enableJerseyFeatures(config.getEnabledJerseyFeatures(), environment);
		disableJerseyFeatures(config.getDisabledJerseyFeatures(), environment);
	}
}

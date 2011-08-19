package nfrancois.poc.jerseyguiceappengine;

import java.util.HashMap;
import java.util.Map;

import nfrancois.poc.jerseyguiceappengine.resource.HelloResource;
import nfrancois.poc.jerseyguiceappengine.service.HelloService;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceServletConfig extends GuiceServletContextListener {
	
	private static final String HELLO_SERVER_RESOURCES_PACKAGE = HelloResource.class.getPackage().getName();
	private static final String JERSEY_CONFIG_PROPERTY_PACKAGES = "com.sun.jersey.config.property.packages";	

	@Override
	protected Injector getInjector() {
		final Map<String, String> params = new HashMap<String, String>();
		params.put(JERSEY_CONFIG_PROPERTY_PACKAGES, HELLO_SERVER_RESOURCES_PACKAGE);
		
		return Guice.createInjector(new ServletModule() {

			@Override
			protected void configureServlets() {
				bind(HelloService.class);
				bind(HelloResource.class);
				serve("/*").with(GuiceContainer.class, params);
			}
		});
	}
}
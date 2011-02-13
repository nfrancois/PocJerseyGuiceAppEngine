package nfrancois.poc.jerseyguiceappengine;

import nfrancois.poc.jerseyguiceappengine.resource.HelloResource;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceServletConfig extends GuiceServletContextListener {

//	private static final String POC_SERVER_RESOURCES_PACKAGE = HelloResource.class.getPackage().getName();
//	private static final String JERSEY_CONFIG_PROPERTY_PACKAGES = "com.sun.jersey.config.property.packages";

	@Override
	protected Injector getInjector() {

		return Guice.createInjector(new ServletModule() {

			@Override
			protected void configureServlets() {
				// Bind classes
				bind(HelloResource.class);

				serve("/*").with(GuiceContainer.class);
			}
		});
	}
}
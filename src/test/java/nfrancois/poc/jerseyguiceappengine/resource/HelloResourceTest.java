package nfrancois.poc.jerseyguiceappengine.resource;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import javax.ws.rs.core.MediaType;

import nfrancois.poc.jerseyguiceappengine.service.HelloService;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class HelloResourceTest extends JerseyTest {
	
	private static Injector injector;	
	private HelloService helloServiceMock;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		HelloResource helloResource =  injector.getInstance(HelloResource.class);
		helloServiceMock = mock(HelloService.class);
		helloResource.setHelloService(helloServiceMock);
	}	
	
	@Override
	protected AppDescriptor configure() {
		injector = Guice.createInjector(new ServletModule() {
			@Override
			protected void configureServlets() {
				bind(HelloResource.class);
				serve("/*").with(GuiceContainer.class);
			}
		});		
		return new WebAppDescriptor.Builder()
							        .contextListenerClass(GuiceTestConfig.class)
							        .filterClass(GuiceFilter.class)
							        .servletPath("/")
							        .build();
	}	
	@Test
	public void shoulReplyHello(){
		String name ="Nicolas";
		String hello = "Hello "+name;
		when(helloServiceMock.saysHelloToSomeone(name)).thenReturn(hello);
		
		ClientResponse response = resource().path("hello").path(name).get(ClientResponse.class);
		
		verify(helloServiceMock).saysHelloToSomeone(name);
		assertThat(response.getClientResponseStatus()).isEqualTo(Status.OK);
		assertThat(response.getType()).isEqualTo(MediaType.TEXT_PLAIN_TYPE);
		assertThat(response.getEntity(String.class)).isEqualTo("Hello Nicolas");
		
	}
	
	@Test
	public void shouldSendHello(){
		String name ="Nicolas";
		String hello = "Hello "+name;
		when(helloServiceMock.sendHello(name)).thenReturn(hello);

		ClientResponse response = resource().path("hello").post(ClientResponse.class,name);

		verify(helloServiceMock).sendHello(name);
		assertThat(response.getClientResponseStatus()).isEqualTo(Status.CREATED);
		assertThat(response.getType()).isEqualTo(MediaType.TEXT_PLAIN_TYPE);
//		assertThat(response.getEntity(String.class)).isEqualTo("Hello Nicolas");
	}
	
	private class GuiceTestConfig extends GuiceServletContextListener {
		@Override
		public Injector getInjector() {
			return injector;
		}
	}	
}

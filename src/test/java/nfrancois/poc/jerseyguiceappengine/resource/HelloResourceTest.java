package nfrancois.poc.jerseyguiceappengine.resource;

import static org.fest.assertions.Assertions.assertThat;

import javax.ws.rs.core.MediaType;

import nfrancois.poc.jerseyguiceappengine.GuiceServletConfig;

import org.junit.Test;

import com.google.inject.servlet.GuiceFilter;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class HelloResourceTest extends JerseyTest {
	
    public HelloResourceTest() {
        super(new WebAppDescriptor.Builder()
                .contextListenerClass(GuiceServletConfig.class)
                .filterClass(GuiceFilter.class)
                .servletPath("/")
                .build());
    }
	
	
	@Test
	public void shoulReplyHello(){
		String relativeUrl = "hello";
		String name ="Nicolas";
		WebResource path = resource().path(relativeUrl).path(name);
		ClientResponse response = path.get(ClientResponse.class);
		assertThat(response.getType()).isEqualTo(MediaType.TEXT_PLAIN_TYPE);
		assertThat(response.getEntity(String.class)).isEqualTo("Hello Nicolas");
	}
}

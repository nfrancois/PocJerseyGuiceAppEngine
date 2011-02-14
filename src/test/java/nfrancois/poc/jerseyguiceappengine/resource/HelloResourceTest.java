package nfrancois.poc.jerseyguiceappengine.resource;

import static org.fest.assertions.Assertions.assertThat;

import javax.ws.rs.core.MediaType;

import nfrancois.poc.jerseyguiceappengine.GuiceServletConfig;

import org.fest.assertions.Assertions;
import org.junit.Test;

import com.google.inject.servlet.GuiceFilter;
import com.sun.jersey.api.client.ClientResponse.Status;
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
		Assertions.assertThat(path.getURI().toString()).isEqualTo(getFullUrl(relativeUrl, name));
		assertThat(path.getRequestBuilder().head().getType()).isEqualTo(MediaType.TEXT_PLAIN_TYPE);
		int status = path.getRequestBuilder().head().getStatus();
		Assertions.assertThat(status).isEqualTo(Status.OK.getStatusCode());
		String response = path.get(String.class);
		Assertions.assertThat(response).isEqualTo("Hello Nicolas");
	}

	private String getFullUrl(String relativeUrl, String name){
		return getBaseURI().toString()+relativeUrl+"/"+name;
	}	
	
}

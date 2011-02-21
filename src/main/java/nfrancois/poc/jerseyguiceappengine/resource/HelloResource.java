package nfrancois.poc.jerseyguiceappengine.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nfrancois.poc.jerseyguiceappengine.service.HelloService;

@Path("hello")
@Singleton
@Produces(MediaType.TEXT_PLAIN)
public class HelloResource {
	
	@Inject
	private HelloService helloService;
	
	@GET
	@Path("/{name}")
	public String reply(@PathParam("name") String name){
		return helloService.saysHelloToSomeone(name);
	}
	
	public void setHelloService(HelloService helloService) {
		this.helloService = helloService;
	}
	
}

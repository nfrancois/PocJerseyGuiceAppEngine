package nfrancois.poc.jerseyguiceappengine.resource;

import java.net.URI;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import nfrancois.poc.jerseyguiceappengine.service.HelloService;

@Path("hello")
@Singleton
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class HelloResource {
	
	@Context 
	UriInfo uriInfo;
	
	@Inject
	private HelloService helloService;
	
	@GET
	@Path("/{name}")
	public String reply(@PathParam("name") String name){
		return helloService.saysHelloToSomeone(name);
	}
	

	@POST
	public Response send(String name){
		String hello = helloService.sendHello(name);
		URI uri = uriInfo.getAbsolutePathBuilder().path(hello).build();
		return Response.created(uri).build();
	}	
	
	
	public void setHelloService(HelloService helloService) {
		this.helloService = helloService;
	}
	
}

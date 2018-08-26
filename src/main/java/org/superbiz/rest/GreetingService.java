package org.superbiz.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/greeting")
public class GreetingService {

	@GET
	@Produces("text/plain")
	public String message() {
		return "Hi Rest!";
	}
	
	@POST
	@Produces("text/plain")
	public String lowerCaseMessage() {
		return "Hi Rest!".toLowerCase();
		
	}
}

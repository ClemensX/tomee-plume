package org.superbiz.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/greeting")
public class GreetingService {

	@GET
	public String message() {
		return "Hi Rest!";
	}
	
	@POST
	public String lowerCaseMessage() {
		return "Hi Rest!".toLowerCase();
		
	}
}

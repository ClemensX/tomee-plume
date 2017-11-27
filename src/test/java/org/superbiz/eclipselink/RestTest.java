package org.superbiz.eclipselink;


import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.openejb.jee.SingletonBean;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.EnableServices;
import org.apache.openejb.testing.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.superbiz.rest.GreetingService;

import junit.framework.TestCase;


@EnableServices(value = "jaxrs")
@RunWith(ApplicationComposer.class)
public class RestTest extends TestCase {

	@Module
	public SingletonBean app() {
		return (SingletonBean) new SingletonBean(GreetingService.class);
	}
	
	@Test
	public void get() throws Exception {
		String message = WebClient.create("http://localhost:4204").path("/RestTest/greeting/").get(String.class);
		System.out.println("REST service answered: " + message + "\n");
        assertEquals("Hi Rest!", message);
    }

	@Test
	public void post() throws Exception {
		String message = WebClient.create("http://localhost:4204").path("/RestTest/greeting/").post("", String.class);
		System.out.println("REST service answered: " + message + "\n");
        assertEquals("hi rest!", message);
    }
}

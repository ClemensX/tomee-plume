package org.superbiz.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.jaxrs.client.WebClient;

/**
 * Servlet implementation class RestClient
 */
@WebServlet("/RestClient")
public class RestClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RestClient() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		url = url.replace("/RestClient", "/greeting");
		response.getWriter().append("\ncall rest service at: ").append(url);
		String message = WebClient.create(url).get(String.class);
		//System.out.println("REST service answered: " + message + "\n");
		response.getWriter().append("\nREST service answered: ").append(message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package org.superbiz.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetEnvVars
 */
@WebServlet("/getenv")
public class GetEnvVars extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetEnvVars() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("\nrbs_jdbc_url: ").append(System.getenv("rbs_jdbc_url"));
		response.getWriter().append("\nrbs_jdbc_user: ").append(System.getenv("rbs_jdbc_user"));
		response.getWriter().append("\nrbs_jdbc_password: ").append(System.getenv("rbs_jdbc_password"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package org.superbiz.servlet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.sap.xs2.security.container.SecurityContext;
//import com.sap.xs2.security.container.UserInfo;
//import com.sap.xs2.security.container.UserInfoException;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@WebServlet("/Hello")
public class Hello extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        ServletOutputStream out = response.getOutputStream();
		out.println("principal" + request.getUserPrincipal());
		out.println("principal Display role " + request.isUserInRole("Display"));
		out.println("principal Supporter role " + request.isUserInRole("Supporter"));

        Map<String, Object> bindings = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
        try {
            Context context = (Context) new InitialContext().lookup("java:comp/");
            addBindings("", bindings, context);
        } catch (NamingException e) {
            throw new ServletException(e);
        }

        out.println("JNDI Context:");
        for (Map.Entry<String, Object> entry : bindings.entrySet()) {
            if (entry.getValue() != null) {
                out.println("  " + entry.getKey() + "=" + entry.getValue());
            } else {
                out.println("  " + entry.getKey());
            }
        }
//        try {
//        	out.println("try user info...");
//            UserInfo userInfo = SecurityContext.getUserInfo();
//        	out.println("user info read ok");
//            String logonName = userInfo.getLogonName(); 
//            String email = userInfo.getEmail(); 
//            String givenName = userInfo.getJsonValue("givenName"); 
//            String familyName = userInfo.getJsonValue("familyName"); 
//        } catch (UserInfoException e) { 
//            out.println(e.toString());
//        }
    }

    private void addBindings(String path, Map<String, Object> bindings, Context context) {
        try {
            for (NameClassPair pair : Collections.list(context.list(""))) {
                String name = pair.getName();
                String className = pair.getClassName();
                if ("org.apache.naming.resources.FileDirContext$FileResource".equals(className)) {
                    bindings.put(path + name, "<file>");
                } else {
                    try {
                        Object value = context.lookup(name);
                        if (value instanceof Context) {
                            Context nextedContext = (Context) value;
                            bindings.put(path + name, "");
                            addBindings(path + name + "/", bindings, nextedContext);
                        } else {
                            bindings.put(path + name, value);
                        }
                    } catch (NamingException e) {
                        // lookup failed
                        bindings.put(path + name, "ERROR: " + e.getMessage());
                    }
                }
            }
        } catch (NamingException e) {
            bindings.put(path, "ERROR: list bindings threw an exception: " + e.getMessage());
        }
    }
}

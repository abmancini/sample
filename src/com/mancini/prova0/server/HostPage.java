package com.mancini.prova0.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.mancini.prova0.shared.UserData;

public class HostPage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String modulename = "prova0";

	private static final String JS_SCRIPT = modulename +"/"+ modulename + ".nocache.js";

	private static final String UNAUTHENTICATED_REDIRECT_DESTINATION = "AuthorizationRequiredPage.html";

	private static final String PAGE_TITLE = "Sample Application";


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();

		if(session == null) { //no session, auth
			response.sendRedirect(UNAUTHENTICATED_REDIRECT_DESTINATION);
		} else {
			
			UserData userData = (UserData) session.getAttribute("userData");
			
			if(userData == null) { //invalid session
				session.invalidate();
				response.sendRedirect(UNAUTHENTICATED_REDIRECT_DESTINATION);	
			} else {		

				//serialize usrData in JSON (using autobeans)
				AutoBean<UserData> bean = AutoBeanUtils.getAutoBean(userData);
				String userDataJson = AutoBeanCodex.encode(bean).getPayload();
				//System.err.println(userDataJson);
				
				generateHostpage(response,userDataJson);
			}
		}
	}


		private void generateHostpage(HttpServletResponse response, String userDataJson) throws IOException {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			out.println("<!doctype html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta http-equiv=\"content-type\"content=\"text/html; charset=UTF-8\">");
			//out.println("<link type="text/css"rel="stylesheet"href="Prova0.css">");
			out.println("<title>"+ PAGE_TITLE +"</title>");
			out.println("<script type=\"text/javascript\"language=\"javascript\"src=\""+JS_SCRIPT+"\"></script>");
			out.println("</head>");
			out.println("<body>");
			out.println("<script type=\"text/javascript\">");
			out.println("var currentUser = " + userDataJson+";");
			out.println("</script>");
			out.println("<noscript>");
			out.println("<div style=\"width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif\">");
			out.println("Your web browser must have JavaScript enabled in order for this application to display correctly.");
			out.println("</div>");
			out.println("</noscript>");
			out.println("</body>");
			out.println("</html>");

		}

	}

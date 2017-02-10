package com.mancini.prova0.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.services.oauth2.model.Userinfoplus;
import com.google.api.services.plus.model.Person;

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
			Userinfoplus userinfo = (Userinfoplus) session.getAttribute("userinfo");
			Person me_person = (Person) session.getAttribute("me_person");
			
			if(userinfo == null) { //invalid session
				session.invalidate();
				response.sendRedirect(UNAUTHENTICATED_REDIRECT_DESTINATION);	
			} else {		
				generateHostpage(response,userinfo, !(me_person.getImage().getIsDefault()));
			}
		}
	}


		private void generateHostpage(HttpServletResponse response, Userinfoplus userinfo, boolean realPicture) throws IOException {

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
			out.println("var currentUser = " + userinfo.toPrettyString() +"");
			if(realPicture)
				out.println("var currentUserMoreData = {\"realPicture\" : true};");
			else
				out.println("var currentUserMoreData = {}");
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

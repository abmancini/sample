package com.mancini.prova0.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;


public class AuthServletCallback extends AbstractAuthorizationCodeCallbackServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential)
			throws ServletException, IOException {		

		Oauth2 oauth2 =
				new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
				.setApplicationName("Oauth2").build();


		//fetch data
		Userinfoplus userinfo = oauth2.userinfo().get().execute();

		//fetch image info using plus-api
		//i need this to detect if the profile image is the default one
		Plus plus = new Plus.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
				.setApplicationName("GobettiVoltaSample").build();
		Person me_person = plus.people().get(userinfo.getId()).setFields("image").execute();


		
		//put data in the session
		HttpSession session = req.getSession(true); //create or get the sessions

		
		session.setAttribute("userinfo", userinfo);
		session.setAttribute("me_person", me_person);

		
		//redirect to the application that will use the data in the session
		resp.sendRedirect("/");
	}

	@Override
	protected void onError(
			HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponse)
					throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong: " + errorResponse.getError());

	}


	@Override
	protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
		return AuthUtils.getRedirectUri(req);
	}

	@Override
	protected AuthorizationCodeFlow initializeFlow() throws IOException {
		return AuthUtils.initializeFlow();
	}

	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
		return AuthUtils.getUserId(req);
	}

}
package com.mancini.prova0.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;

public class AuthServlet extends AbstractAuthorizationCodeServlet {

	private static final long serialVersionUID = 1L;

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


package com.mancini.prova0.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mancini.prova0.client.services.AccountServices;
import com.mancini.prova0.client.services.Balance;

public class AccountServicesImpl extends RemoteServiceServlet  
implements AccountServices {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Balance getBalance(String asset) {
		return new Balance()	;
	}




}

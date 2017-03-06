package com.mancini.prova0.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("accounts")
public interface AccountServices extends RemoteService  {

	public Balance getBalance(String asset);
	
}

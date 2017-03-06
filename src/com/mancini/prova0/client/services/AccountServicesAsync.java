package com.mancini.prova0.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AccountServicesAsync {

	void getBalance(String asset, AsyncCallback<Balance> callback);

}

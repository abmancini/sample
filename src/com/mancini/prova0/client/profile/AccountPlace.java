package com.mancini.prova0.client.profile;

import com.google.web.bindery.event.shared.EventBus;
import com.mancini.prova0.client.ClientFactory;
import com.mancini.prova0.client.MyPlace;

public class AccountPlace extends MyPlace<AccountActivity> {
   
	
	public static final String prefix = "account";
	
	@Override
	public String getPrefixedToken() {
		return prefix;
	}
	
	
    public AccountPlace(String token) {
    }

 
    
    @Override
	public AccountActivity getActivity(EventBus eventBus, ClientFactory clientFactory) {
		return new AccountActivity(this, clientFactory);
	}


}
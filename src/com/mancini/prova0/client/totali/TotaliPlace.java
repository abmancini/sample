package com.mancini.prova0.client.totali;

import com.google.web.bindery.event.shared.EventBus;
import com.mancini.prova0.client.ClientFactory;
import com.mancini.prova0.client.MyPlace;

public class TotaliPlace extends MyPlace<TotaliActivity> {
   
	
	public static final String prefix = "totali";
	
	@Override
	public String getPrefixedToken() {
		return prefix;
	}
	
    public TotaliPlace(String token) {
    }
    
    @Override
	public TotaliActivity getActivity(EventBus eventBus, ClientFactory clientFactory) {
		return new TotaliActivity(this, clientFactory);
	}


}
package com.mancini.prova0.client.info;

import com.google.web.bindery.event.shared.EventBus;
import com.mancini.prova0.client.ClientFactory;
import com.mancini.prova0.client.MyPlace;
import com.mancini.prova0.client.home.HelloActivity;
import com.mancini.prova0.client.home.HelloPlace;

public class InfoPlace extends MyPlace<HelloActivity> {
	
	
	
	public static final String prefix = "info";
	@Override
	public String getPrefixedToken() {
		return prefix;
	}
	

	
	
    private String helloName;

    public InfoPlace(String token) {
        this.helloName = token;
    }

    public String getHelloName() {
        return helloName;
    }

    
	@Override
	public HelloActivity getActivity(EventBus eventBus, ClientFactory clientFactory) {
		return new HelloActivity(new HelloPlace("io sono info"), clientFactory);
	}
}
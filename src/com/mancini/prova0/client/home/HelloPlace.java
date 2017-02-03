package com.mancini.prova0.client.home;

import com.google.web.bindery.event.shared.EventBus;
import com.mancini.prova0.client.ClientFactory;
import com.mancini.prova0.client.MyPlace;

public class HelloPlace extends MyPlace<HelloActivity> {
   
	
	public static final String prefix = "HeLlO";
	
	@Override
	public String getPrefixedToken() {
		return prefix + ((helloName == null) ? "" : (":" +helloName));
	}
	
	
	private String helloName;

    public HelloPlace(String token) {
        this.helloName = token;
    }

    public String getHelloName() {
        return helloName;
    }
 
    
    @Override
	public HelloActivity getActivity(EventBus eventBus, ClientFactory clientFactory) {
		return new HelloActivity(this, clientFactory);
	}


}
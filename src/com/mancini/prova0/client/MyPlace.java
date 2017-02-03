package com.mancini.prova0.client;

import com.google.gwt.place.shared.Place;
import com.google.web.bindery.event.shared.EventBus;

public abstract class  MyPlace<T extends MyActivity> extends Place {

	//factory like place
	public abstract T getActivity(EventBus eventBus, ClientFactory clientFactory);
	
	
	//should return PLACE_PREFIX:STATE_TOKEN
	public abstract String getPrefixedToken();
	
	
}

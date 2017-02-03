package com.mancini.prova0.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;

public class AppActivityMapper implements ActivityMapper {
    private ClientFactory clientFactory;

    public AppActivityMapper(ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
    	
    	if(place instanceof MyPlace) {
    		MyPlace<?> mp = (MyPlace<?>)place;

    		//i'm not confortable with the bus inside the clientfactory so ...
    		MyActivity a = mp.getActivity(clientFactory.getEventBus(),clientFactory);    		
    		
    		return a;
    	}
    	
    	
    	Window.alert("ERROR: unknow place: " + place);
    	return null;
    }
}
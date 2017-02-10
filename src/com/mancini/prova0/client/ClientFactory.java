package com.mancini.prova0.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.mancini.prova0.client.applayout.AppView;
import com.mancini.prova0.client.home.HelloView;

public class ClientFactory {
    private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);
    private AppView appView = null;
	private HelloView helloView = null;
	private AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);


    //@Override
    public EventBus getEventBus() {
        return eventBus;
    }
    
    
    //@Override 
    public AppView getAppView(AppView.Presenter presenter) { 	
    	if(appView == null)
    		appView = new AppView();
    	
    	appView.setPresenter(presenter);
        return appView;
    }


	public PlaceController getPlaceController() {
		return placeController;
	}


	public HelloView getHelloView(HelloView.Presenter presenter) {
		if(helloView == null)
			helloView = new HelloView();
		helloView.setPresenter(presenter);
		return helloView;
	}


	public void goTo(String prefix) {
		placeController.goTo( historyMapper.getPlace(prefix));
	}


	public AppPlaceHistoryMapper getHistoryMapper() {
		return historyMapper;
	}
    
    
    
}
package com.mancini.prova0.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public class ClientFactory {
    private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);
    private AppView appView = null;
	private HelloView helloView = null;

    //@Override
    public EventBus getEventBus() {
        return eventBus;
    }
    
    
    //@Override 
    public AppView getAppView() {
    	
    	if(appView == null)
    		appView = new AppView();
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
    
    
    
}
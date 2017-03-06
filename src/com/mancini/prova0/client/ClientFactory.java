package com.mancini.prova0.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.mancini.prova0.client.applayout.AppView;
import com.mancini.prova0.client.home.HelloView;
import com.mancini.prova0.client.profile.AccountView;
import com.mancini.prova0.client.totali.TotaliView;

public class ClientFactory {
    private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);
	private AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);

    private AppView appView;
	private HelloView helloView ;
	private AccountView accountView;
	private TotaliView totaliView;


    //@Override
    public EventBus getEventBus() {
        return eventBus;
    }
    public void goTo(String prefix) {
		placeController.goTo( historyMapper.getPlace(prefix));
	}


	public AppPlaceHistoryMapper getHistoryMapper() {
		return historyMapper;
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

	public AccountView getAccountView(AccountView.Presenter presenter) {
		if(accountView == null)
			accountView = new AccountView();
		accountView.setPresenter(presenter);
		return accountView;
	}
	public TotaliView getTotaliView(TotaliView.Presenter presenter) {
		if(totaliView == null)
			totaliView = new TotaliView();
			totaliView.setPresenter(presenter);
		return totaliView;
	}
    
    
    
}
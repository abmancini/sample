package com.mancini.prova0.client.totali;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mancini.prova0.client.ClientFactory;
import com.mancini.prova0.client.MyActivity;
import com.mancini.prova0.client.services.AccountServices;
import com.mancini.prova0.client.services.AccountServicesAsync;
import com.mancini.prova0.client.services.Balance;

public class TotaliActivity extends MyActivity implements TotaliView.Presenter {

	// Used to obtain views, eventBus, placeController
	private ClientFactory clientFactory;
	

	private static AccountServicesAsync accountServicesAsync 
			= GWT.create(AccountServices.class);

	public TotaliActivity(TotaliPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		TotaliView view = clientFactory.getTotaliView(this);
		containerWidget.setWidget(view.asWidget());

		view.setWaiting();

		accountServicesAsync.getBalance("prova",  new AsyncCallback<Balance>() {
			
			@Override
			public void onSuccess(Balance result) {
				//view.setBalance(result);
				view.setReady();
				Window.alert("Balance Arrivato" + result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				view.setReady();
			}
		});


	}

	//    /**
	//     * Ask user before stopping this activity
	//     */
	//    @Override
	//    public String mayStop() {
	//        return "Please hold on. This activity is stopping.";
	//    }

	/**
	 * Navigate to a new Place in the browser
	 */
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}
}
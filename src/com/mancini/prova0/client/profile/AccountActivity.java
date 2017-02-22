package com.mancini.prova0.client.profile;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mancini.prova0.client.ClientFactory;
import com.mancini.prova0.client.MyActivity;

public class AccountActivity extends MyActivity implements AccountView.Presenter {

	// Used to obtain views, eventBus, placeController
    private ClientFactory clientFactory;

    public AccountActivity(AccountPlace place, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        AccountView view = clientFactory.getAccountView(this);
        containerWidget.setWidget(view.asWidget());
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
package com.mancini.prova0.client.profile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class AccountView extends Composite {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);
	interface ViewUiBinder extends UiBinder<Widget, AccountView> {
	}

	public interface Presenter {
		void goTo(Place place);
	}


	
	@SuppressWarnings("unused")
	private Presenter presenter;

	public AccountView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	//@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
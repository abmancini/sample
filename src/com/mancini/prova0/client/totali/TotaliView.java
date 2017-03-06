package com.mancini.prova0.client.totali;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLoader;


public class TotaliView extends Composite {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);
	interface ViewUiBinder extends UiBinder<Widget, TotaliView> {
	}

	public interface Presenter {
		void goTo(Place place);
	}


	
	@SuppressWarnings("unused")
	private Presenter presenter;

	public TotaliView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	//@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}


	public void setWaiting() {
		MaterialLoader.showLoading(true);
	}
	
	public void setReady() {
		MaterialLoader.showLoading(false);
	}
}
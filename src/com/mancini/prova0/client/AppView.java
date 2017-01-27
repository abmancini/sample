package com.mancini.prova0.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class AppView extends Composite implements AcceptsOneWidget {

	private static AppViewUiBinder uiBinder = GWT.create(AppViewUiBinder.class);

	interface AppViewUiBinder extends UiBinder<Widget, AppView> {
	}

	public AppView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	@UiField
	SimplePanel body;


	@Override
	public void setWidget(IsWidget w) {
		body.setWidget(w);
	}
	

}

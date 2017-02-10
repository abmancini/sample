package com.mancini.prova0.client.applayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialContainer;

public class AppView extends Composite implements AcceptsOneWidget {

	private static AppViewUiBinder uiBinder = GWT.create(AppViewUiBinder.class);

	interface AppViewUiBinder extends UiBinder<Widget, AppView> {
	}

	
	//the presenter of this view
	public interface Presenter extends Menu.Presenter { //we embed a Menu that needs his own presenter 
		
	}

	private Presenter presenter;
	public void setPresenter(Presenter presenter) {
		this.presenter= presenter;
		menu.setPresenter(this.presenter); // we should take care of the embedded menu's presenter.
	}
	
	public AppView() {
		initWidget(uiBinder.createAndBindUi(this));

	}


	@UiField
	MaterialContainer body;

	@UiField
	Menu menu;
	

	@Override
	public void setWidget(IsWidget w) {

		body.clear();

		//needed cause MAterialContainer and HTMLPanel actually have problems with null
		// widget insertions, that is common with a&p
		if(w == null) 
			return; 

		body.add(w);
	}

	public Menu getMenu() {
		return menu;
	}

}

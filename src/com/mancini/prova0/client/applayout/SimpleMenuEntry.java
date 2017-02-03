package com.mancini.prova0.client.applayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.mancini.prova0.client.ClientFactory;
import com.mancini.prova0.client.Prova0.MenuEntryData;

import gwt.material.design.client.ui.MaterialLink;

public class SimpleMenuEntry extends Composite {

	private static SimpleMenuEntryUiBinder uiBinder = GWT.create(SimpleMenuEntryUiBinder.class);

	interface SimpleMenuEntryUiBinder extends UiBinder<Widget, SimpleMenuEntry> {
	}

	public SimpleMenuEntry(MenuEntryData data, final ClientFactory clientFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		link.setIconType(data.iconType);
		link.setText(data.labelText);
		link.setTargetHistoryToken(data.token);
		
		
		//let us to have both control on the activity change 
		//and on ctrl-clicks
		link.addClickHandler( new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clientFactory.goTo(data.token);
				event.preventDefault();
			}
		});
	}

	
	
	@UiField
	MaterialLink link;
	
	
	
}

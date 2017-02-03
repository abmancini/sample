package com.mancini.prova0.client.applayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialNavBar;
import gwt.material.design.client.ui.MaterialSideNav;

public class Menu extends Composite {

	private static AppViewUiBinder uiBinder = GWT.create(AppViewUiBinder.class);

	interface AppViewUiBinder extends UiBinder<Widget, Menu> {
	}


	@UiField
	MaterialNavBar navBar;

	@UiField
	MaterialSideNav sideNav;

	@UiField
	MaterialChip userDisplayName;

	public Menu() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public MaterialSideNav getSideNav() {
		return sideNav;
	}

	public MaterialNavBar getNavBar() {
		return navBar;
	}
	
	public void setCurrentUserName(String name) {
		
		userDisplayName.setText(name);
		userDisplayName.setLetter(name.substring(0,1).toUpperCase());
	}

	public void addMenuEntry(SimpleMenuEntry simpleMenuEntry) {
		sideNav.add(simpleMenuEntry);
	}







}

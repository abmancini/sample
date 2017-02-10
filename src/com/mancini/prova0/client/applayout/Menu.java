package com.mancini.prova0.client.applayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialNavBar;
import gwt.material.design.client.ui.MaterialSideNav;

public class Menu extends Composite {

	private static AppViewUiBinder uiBinder = GWT.create(AppViewUiBinder.class);

	interface AppViewUiBinder extends UiBinder<Widget, Menu> {
	}


	//the view presenter 
	public interface Presenter {
		public void logout();
	}

	Presenter presenter;

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}


	@UiField
	MaterialNavBar navBar;

	@UiField
	MaterialSideNav sideNav;

	@UiField
	MaterialChip userDisplayName;

	@UiField
	MaterialIcon logoutTrigger;

	@UiHandler("logoutTrigger")
	public void userDisplayNameClick(ClickEvent event) {			
		//the default for chips is to hide that is not intended here
		event.preventDefault();
		event.stopPropagation();
		presenter.logout();
	}


	public Menu() {
		initWidget(uiBinder.createAndBindUi(this));
		///presenter is null here, setPresenter MUST be called
	}

	public MaterialSideNav getSideNav() {
		return sideNav;
	}

	public MaterialNavBar getNavBar() {
		return navBar;
	}



	public void setCurrentUserDisplayData(String name, String picture) {

		userDisplayName.setText(name);
		if(picture == null || picture.trim().length() ==0)
			userDisplayName.setLetter(name.substring(0,1).toUpperCase());
		else
			userDisplayName.setUrl(picture);
	}

	public void addMenuEntry(SimpleMenuEntry simpleMenuEntry) {
		sideNav.add(simpleMenuEntry);
	}







}

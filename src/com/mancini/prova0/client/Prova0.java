package com.mancini.prova0.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.mancini.prova0.client.applayout.AppView;
import com.mancini.prova0.client.applayout.SimpleMenuEntry;
import com.mancini.prova0.client.home.HelloPlace;
import com.mancini.prova0.client.info.InfoPlace;

import gwt.material.design.client.constants.IconType;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Prova0 implements EntryPoint, UncaughtExceptionHandler, ScheduledCommand {

	
	public static class MenuEntryData {
		public String labelText;
		public String token;
		public IconType iconType;
		public MenuEntryData(String labelText, IconType icon, String token) {
			this.labelText = labelText;
			this.token = token;
			this.iconType = icon;
		}
	}



	private Place defaultPlace = new HelloPlace("World!");

	private static final List<MenuEntryData> mainMenu = 
			Arrays.asList( 
					new MenuEntryData("Home", IconType.HOME,  HelloPlace.prefix),
					new MenuEntryData("Info", IconType.INFO,  InfoPlace.prefix)
					);

	



	public void onModuleLoad() {	
		GWT.setUncaughtExceptionHandler( this); 
		Scheduler.get().scheduleDeferred( this );
	}





	@Override
	public void execute() {
		//get the user data from the hostpage
		Dictionary currentUser = Dictionary.getDictionary("currentUser");
		
		if(currentUser == null) {
			Window.alert("No User Info Available");
			return;
		} 
		
		ClientFactory clientFactory = GWT.create(ClientFactory.class);

		AppView appView = clientFactory.getAppView();

		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(appView);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper= clientFactory.getHistoryMapper();

		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		
		
		appView.getMenu().setCurrentUserName(currentUser.get("displayName"));

		
		//setup the menu
		for( MenuEntryData me: mainMenu ) {
			appView.getMenu().addMenuEntry( new SimpleMenuEntry(me, clientFactory));
		}

		
		
		


		RootPanel.get().add(appView);
		// Goes to the place represented on URL else default place
		historyHandler.handleCurrentHistory();
		
	}





	@Override
	public void onUncaughtException(Throwable e) {
		Window.alert("Fatal Error: " + e.getMessage());
		e.printStackTrace();
	}

}

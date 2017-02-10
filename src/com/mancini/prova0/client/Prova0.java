package com.mancini.prova0.client;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.mancini.prova0.client.applayout.AppView;
import com.mancini.prova0.client.applayout.AppView.Presenter;
import com.mancini.prova0.client.applayout.SimpleMenuEntry;
import com.mancini.prova0.client.home.HelloPlace;
import com.mancini.prova0.client.info.InfoPlace;

import gwt.material.design.client.constants.IconType;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Prova0 implements EntryPoint, UncaughtExceptionHandler, ScheduledCommand, Presenter {

	
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
		Dictionary currentUserMoreData = Dictionary.getDictionary("currentUserMoreData");
		
		if(currentUser == null) {
			Window.alert("No User Info Available");
			return;
		} 
		
		ClientFactory clientFactory = GWT.create(ClientFactory.class);

		AppView appView = clientFactory.getAppView(this);

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

		
		
		
		//naive and cumbersome, a native type for userinfo would be really better
		Set<String> attrs = currentUser.keySet();
		Set<String> moreAttrs = currentUserMoreData.keySet();
		
		String picture = null;
		if(attrs.contains("picture") && moreAttrs.contains("realPicture"))
			picture = currentUser.get("picture");
		
		String name = currentUser.get("name");
		//String email = currentUser.get("email");
		
		
		if(name == null || name.trim().length() == 0) { //use the email as a fallback
			name = currentUser.get("email");
		}
		
		
		 
		appView.getMenu().setCurrentUserDisplayData(name,picture);

		
		
		
		
		
		
		
		
		
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




	//We impement AppView PResenter 
	@Override
	public void logout() {		
		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, "/logout");
		try {
			requestBuilder.sendRequest("", new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
						if(response.getStatusCode() == Response.SC_OK) 
							Window.Location.reload(); //reload so that the application will behave as not authenticated 
						else
							Window.alert("Problem during logout: " + response.getStatusCode() + " " + response.getStatusText() );
				}
				@Override
				public void onError(Request request, Throwable exception) {
					Window.alert("ERROR during logout: " + exception.getMessage() );
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
			Window.alert("Exception during logout: " + e.getMessage() );

		}
	}

}

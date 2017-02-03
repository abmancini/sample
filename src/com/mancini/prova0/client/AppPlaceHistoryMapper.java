package com.mancini.prova0.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.mancini.prova0.client.home.HelloPlace;
import com.mancini.prova0.client.info.InfoPlace;

import gwt.material.design.client.constants.IconType;

//@WithTokenizers({HelloPlace.Tokenizer.class,InfoPlace.Tokenizer.class})
public class AppPlaceHistoryMapper implements PlaceHistoryMapper {




	
	private static final MyPlace<?> selectPlace(String prefix, String token) {
		switch (prefix) {
		case HelloPlace.prefix:
			return new HelloPlace(token);
		case InfoPlace.prefix:
			return new InfoPlace(token);
		default:
			return null ;
		}
	}
	
	

	
	
	@Override
	public Place getPlace(String prefixedToken) {

		if(prefixedToken == null)
			return null;
		String parts[] = prefixedToken.split(":", 2);

		String prefix = parts[0];
		String token = (parts.length==1) ? null : parts[1];			

		return selectPlace(prefix,token);
	}

	@Override
	public String getToken(Place place) {
		
		if(place instanceof MyPlace<?>) {
			MyPlace<?> p = (MyPlace<?>)place;
			return p.getPrefixedToken();
		} 
		return null;
	}


}

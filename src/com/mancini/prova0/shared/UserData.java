package com.mancini.prova0.shared;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


//we use this type clientside via JsInterop and serverside via Autobeans
@JsType(isNative=true, namespace=JsPackage.GLOBAL)
public interface UserData {
	
	interface Factory extends AutoBeanFactory {
		 AutoBean<UserData> newInstance();
	}
	
	@JsProperty
	String	getId();
	
	@JsProperty
	String  getEmail();
	
	@JsProperty
	String  getName();
	
	@JsProperty
	String  getPicture();
	
	@JsProperty
	boolean isSilhouette();	

	@JsProperty
	void setId(String s);
	
	@JsProperty
	void setEmail(String s);
	
	@JsProperty
	void setName(String ss);
	
	@JsProperty
	void setPicture(String s);
	
	@JsProperty
	void setSilhouette(boolean b);	

}

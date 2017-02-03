package com.mancini.prova0.client.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.Place;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class HelloView extends Composite {
	
	
    private static HelloViewUiBinder uiBinder = GWT.create(HelloViewUiBinder.class);
    interface HelloViewUiBinder extends UiBinder<Widget, HelloView> {
    }
    
    public interface Presenter {
        void goTo(Place place);
    }

    @UiField
    SpanElement nameSpan;
    @UiField
    Anchor goodbyeLink;
    private Presenter presenter;
    private String name;

    public HelloView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    //@Override
    public void setName(String name) {
        this.name = name;
        nameSpan.setInnerText(name);
    }

    @UiHandler("goodbyeLink")
    void onClickGoodbye(ClickEvent e) {
       // presenter.goTo(new GoodbyePlace(name));
    }

    //@Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
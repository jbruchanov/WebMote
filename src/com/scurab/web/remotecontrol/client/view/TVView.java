package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class TVView extends Composite
{

	private static TVViewUiBinder uiBinder = GWT.create(TVViewUiBinder.class);

	interface TVViewUiBinder extends UiBinder<Widget, TVView>
	{
	}

	public TVView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

}

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

public class PlayerPad extends Composite
{

	private static PlayerPadUiBinder uiBinder = GWT.create(PlayerPadUiBinder.class);

	interface PlayerPadUiBinder extends UiBinder<Widget, PlayerPad>
	{
	}

	public PlayerPad()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

}

package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class JoyPad extends Composite
{
	private static JoyPadUiBinder uiBinder = GWT.create(JoyPadUiBinder.class);

	interface JoyPadUiBinder extends UiBinder<Widget, JoyPad>
	{
	}

	public JoyPad()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
}

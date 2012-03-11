package com.scurab.web.remotecontrol.client.view.keyboard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ArrowsKeyboard extends AbstractKeyboard
{

	private static ArrowsKeyboardUiBinder uiBinder = GWT.create(ArrowsKeyboardUiBinder.class);
	@UiField HTMLPanel container;
	interface ArrowsKeyboardUiBinder extends UiBinder<Widget, ArrowsKeyboard>
	{
	}

	public ArrowsKeyboard()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasWidgets getContainer()
	{
		return container;
	}
}

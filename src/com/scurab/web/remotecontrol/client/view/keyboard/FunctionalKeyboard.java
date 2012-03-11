package com.scurab.web.remotecontrol.client.view.keyboard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLPanel;

public class FunctionalKeyboard extends AbstractKeyboard
{

	private static FunctionalKeyboardUiBinder uiBinder = GWT.create(FunctionalKeyboardUiBinder.class);
	@UiField HTMLPanel container;

	interface FunctionalKeyboardUiBinder extends UiBinder<Widget, FunctionalKeyboard>
	{
	}

	public FunctionalKeyboard()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasWidgets getContainer()
	{
		return container;
	}

}

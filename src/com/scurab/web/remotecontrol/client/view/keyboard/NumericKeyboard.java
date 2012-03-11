package com.scurab.web.remotecontrol.client.view.keyboard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLPanel;

public class NumericKeyboard extends AbstractKeyboard
{

	private static NumericKeyboardUiBinder uiBinder = GWT.create(NumericKeyboardUiBinder.class);
	@UiField HTMLPanel container;

	interface NumericKeyboardUiBinder extends UiBinder<Widget, NumericKeyboard>
	{
	}

	public NumericKeyboard()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasWidgets getContainer()
	{
		return container;
	}
}

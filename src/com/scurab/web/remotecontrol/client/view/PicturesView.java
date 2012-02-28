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

public class PicturesView extends Composite
{

	private static PictiuresViewUiBinder uiBinder = GWT.create(PictiuresViewUiBinder.class);

	interface PictiuresViewUiBinder extends UiBinder<Widget, PicturesView>
	{
	}

	public PicturesView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
}

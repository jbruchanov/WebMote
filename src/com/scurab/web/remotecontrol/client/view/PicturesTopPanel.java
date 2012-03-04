package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class PicturesTopPanel extends Composite
{

	private static PicturesTopPanelUiBinder uiBinder = GWT.create(PicturesTopPanelUiBinder.class);

	interface PicturesTopPanelUiBinder extends UiBinder<Widget, PicturesTopPanel>
	{
	}

	public PicturesTopPanel()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

}

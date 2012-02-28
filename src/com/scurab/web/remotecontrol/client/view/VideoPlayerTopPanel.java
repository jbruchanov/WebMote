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
import com.google.gwt.user.client.ui.ListBox;

public class VideoPlayerTopPanel extends Composite
{

	private static MediaPlayerTopPanelUiBinder uiBinder = GWT.create(MediaPlayerTopPanelUiBinder.class);
	@UiField ListBox cmbItems;

	interface MediaPlayerTopPanelUiBinder extends UiBinder<Widget, VideoPlayerTopPanel>
	{
	}

	public VideoPlayerTopPanel()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		cmbItems.addItem("Hello World!");
	}

}

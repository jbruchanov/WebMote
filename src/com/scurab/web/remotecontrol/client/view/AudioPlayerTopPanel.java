package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AudioPlayerTopPanel extends Composite
{

	private static AudioPlayerTopPanelUiBinder uiBinder = GWT.create(AudioPlayerTopPanelUiBinder.class);

	interface AudioPlayerTopPanelUiBinder extends UiBinder<Widget, AudioPlayerTopPanel>
	{
	}

	public AudioPlayerTopPanel()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
}

package com.scurab.web.remotecontrol.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class AudioPlayerView extends AbstractView
{

	private static AudioPlayerViewUiBinder uiBinder = GWT.create(AudioPlayerViewUiBinder.class);

	interface AudioPlayerViewUiBinder extends UiBinder<Widget, AudioPlayerView>
	{
	}

	public AudioPlayerView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		// TODO Auto-generated method stub
		return null;
	}
}

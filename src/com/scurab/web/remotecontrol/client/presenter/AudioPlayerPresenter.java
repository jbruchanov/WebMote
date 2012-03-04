package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.AudioPlayerView;

public class AudioPlayerPresenter extends BasePresenter
{
	private AudioPlayerView mDisplay = null;
	public AudioPlayerPresenter(DataService dataService, HandlerManager eventBus, AudioPlayerView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
	}
}

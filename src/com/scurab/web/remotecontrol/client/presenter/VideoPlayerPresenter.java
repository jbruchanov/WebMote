package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.VideoPlayerView;

public class VideoPlayerPresenter extends BasePresenter
{
	private VideoPlayerView mDisplay;
	public VideoPlayerPresenter(DataService dataService, HandlerManager eventBus, VideoPlayerView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
	}

}

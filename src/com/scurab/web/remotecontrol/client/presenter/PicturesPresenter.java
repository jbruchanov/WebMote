package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.PicturesView;

public class PicturesPresenter extends BasePresenter
{
	private PicturesView mDisplay = null;
	public PicturesPresenter(DataService dataService, HandlerManager eventBus, PicturesView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
	}

}

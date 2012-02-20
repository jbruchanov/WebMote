package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.IsWidget;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.VolumeControl;

public class VolumeControlPresenter extends BasePresenter
{
	private VolumeControl mDisplay = null;
	
	public VolumeControlPresenter(DataService dataService, HandlerManager eventBus, VolumeControl display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
	}

}

package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.IsWidget;
import com.scurab.web.remotecontrol.client.server.DataService;

public class TVPresenter extends BasePresenter
{

	public TVPresenter(DataService dataService, HandlerManager eventBus, IsWidget display)
	{
		super(dataService, eventBus, display);
	}

}

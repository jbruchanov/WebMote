package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.DiskBrowserItem;
import com.scurab.web.remotecontrol.client.view.DiskBrowserView;

public class DiskBrowserPresenter extends BasePresenter
{
	private DiskBrowserView mDisplay = null;
	
	public DiskBrowserPresenter(DataService dataService, HandlerManager eventBus, DiskBrowserView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
		loadData();
	}
	
	private void loadData()
	{
		for(int i = 0;i<20;i++)
		{
			DiskBrowserItem dbi = new DiskBrowserItem();			
			mDisplay.getContentPanel().add(dbi);
		}
	}
	
}

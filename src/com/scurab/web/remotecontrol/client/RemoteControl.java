package com.scurab.web.remotecontrol.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.scurab.web.remotecontrol.client.view.MainView;
import com.scurab.web.remotecontrol.client.view.RootView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RemoteControl implements EntryPoint
{
	
	public void onModuleLoad()
	{
		RootPanel.get("content").add(new RootView());
	}
}

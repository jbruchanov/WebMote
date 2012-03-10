package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.AbstractView;

public abstract class BaseControlPresenter extends BasePresenter
{
	private AbstractView mDisplay = null;
	
	
	public  BaseControlPresenter(DataService dataService, HandlerManager eventBus, AbstractView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
		onBindCommandButtons();
	}
	
	protected void onSendCommand(String command)
	{
		onSendCommand(getCommand(command));
	}
	
	protected void onSendCommand(Command command)
	{
		try
		{
			mDataService.sendCommand(command);
		}
		catch(Exception e)
		{
			Window.alert(e.getMessage());
		}
	}
	
	protected void onBindCommandButtons()
	{
		if(mDisplay.getClickElements() == null)
			return;
		
		for(IsCommandableClickHandler ic : mDisplay.getClickElements())
		{
			final IsCommandableClickHandler source = ic;
			ic.addClickHandler(new ClickHandler()
			{
				@Override
				public void onClick(ClickEvent event)
				{
					onSendCommand(source.getCommand());
				}
			});
		}
	}
	
	protected abstract Command getCommand(String command);
}

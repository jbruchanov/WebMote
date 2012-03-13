package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.TelevisionCommand;
import com.scurab.web.remotecontrol.client.event.ChangePresenterEvent;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.ConfigView;
import com.scurab.web.remotecontrol.client.view.JoyPad;
import com.scurab.web.remotecontrol.client.view.TVView;

public class TVPresenter extends BaseControlPresenter
{
	private TVView mDisplay = null;
	private Widget mCurrentVisibleWidget = null;
	
	private enum ShowPanel
	{
		Default, Numeric, Recording, User
	}
	
	public TVPresenter(DataService dataService, HandlerManager eventBus, TVView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
		bind();
	}
	
	private void bind()
	{				
		onClickButton(ShowPanel.Default);
		mDisplay.getUserContainer().setVisible(false);
		mDisplay.getNumericPanel().setVisible(false);
		mDisplay.getRecordingPanel().setVisible(false);
		
		mDisplay.getBtnDefault().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onClickButton(ShowPanel.Default);
			}
		});
		
		mDisplay.getBtnNumeric().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{			
				onClickButton(ShowPanel.Numeric);
			}
		});
		
		mDisplay.getBtnRecord().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{		
				onClickButton(ShowPanel.Recording);
			}
		});
		
		mDisplay.getBtnUser().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				mEventBus.fireEvent(new ChangePresenterEvent(new ConfigPresenter(mDataService, mEventBus, new ConfigView())));
			}
		});
		
		
	}
	
	protected void onClickButton(ShowPanel what)
	{
		if(mCurrentVisibleWidget != null)
			mCurrentVisibleWidget.setVisible(false);
		mCurrentVisibleWidget = null;
		switch(what)
		{
			case Default: mCurrentVisibleWidget = mDisplay.getJoyPad();break;
			case Numeric: mCurrentVisibleWidget = mDisplay.getNumericPanel();break;
			case Recording: mCurrentVisibleWidget = mDisplay.getRecordingPanel();break;
			case User: mCurrentVisibleWidget = mDisplay.getUserContainer();break;
		}
		
		if(mCurrentVisibleWidget != null)
			mCurrentVisibleWidget.setVisible(true);
	}
	
	private String translateCommand(String command)
	{
		String result = command;
		if (command.equals(JoyPad.COMMAND_LEFT))
			result = "ChangeVolumeDown";
		else if (command.equals(JoyPad.COMMAND_UP))
			result = "ChangeChannelUp";
		else if (command.equals(JoyPad.COMMAND_RIGHT))
			result = "ChangeVolumeUp";
		else if (command.equals(JoyPad.COMMAND_DOWN))
			result = "ChangeChannelDown";
		else if (command.equals(JoyPad.COMMAND_CENTER))
			result = "ShowInfo";
		return result;
	}

	@Override
	protected Command getCommand(String command)
	{
		TelevisionCommand tvc = new TelevisionCommand(RemoteControl.TVAppliation);
		tvc.Method = translateCommand(command);
		return tvc;
	}

	@Override
	public String getName()
	{
		return "Television";
	}
}

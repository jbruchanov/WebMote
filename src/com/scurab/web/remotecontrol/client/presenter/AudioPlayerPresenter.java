package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.commands.AudioPlayerCommand;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.AudioPlayerView;

public class AudioPlayerPresenter extends BaseControlPresenter
{
	private AudioPlayerView mDisplay = null;
	private Widget mCurrentVisibleWidget;
	
	private enum ShowPanel
	{
		Default, Layout,User
	}
	
	public AudioPlayerPresenter(DataService dataService, HandlerManager eventBus, AudioPlayerView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
		bind();
	}
	
	private void bind()
	{
		onClickButton(ShowPanel.Default);
		mDisplay.getUserPanel().setVisible(false);
		mDisplay.getLayoutPanel().setVisible(false);
		
		mDisplay.getTopPanel().getBtnDefault().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onClickButton(ShowPanel.Default);
			}
		});
		
		mDisplay.getTopPanel().getBtnOptions().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onClickButton(ShowPanel.Layout);
			}
		});
		
		mDisplay.getTopPanel().getBtnUser().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onClickButton(ShowPanel.User);
			}
		});
		
		initFavorities(mDisplay.getTopPanel().getCmbItems(), RemoteControl.PropertyKeys.AUDIOPLAYER);
	}
	
	protected void onClickButton(ShowPanel what)
	{
		if(mCurrentVisibleWidget != null)
			mCurrentVisibleWidget.setVisible(false);
		mCurrentVisibleWidget = null;
		switch(what)
		{
			case Default: mCurrentVisibleWidget =   mDisplay.getPlayerPad();break;
			case Layout: mCurrentVisibleWidget =    mDisplay.getLayoutPanel();break;
			case User: mCurrentVisibleWidget =      mDisplay.getUserPanel();break;			
		}
		
		if(mCurrentVisibleWidget != null)
			mCurrentVisibleWidget.setVisible(true);
	}
	
	@Override
	protected Command getCommand(String command)
	{
		AudioPlayerCommand apc = new AudioPlayerCommand(RemoteControl.AudioPlayer);
		apc.Method =command;
		if(command.equals("Start"))
		{
			ListBox lb = mDisplay.getTopPanel().getCmbItems();
			if(lb.getSelectedIndex() > -1)
			{
				String v = lb.getValue(lb.getSelectedIndex()); 
				if(v != null && v.trim().length() != 0)
					apc.MethodParameter = v;
			}
		}
		return apc;
	}

	@Override
	public String getName()
	{
		return "AudioPlayer";
	}
}

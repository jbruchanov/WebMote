package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.VideoPlayerCommand;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.JoyPad;
import com.scurab.web.remotecontrol.client.view.VideoPlayerView;

public class VideoPlayerPresenter extends BaseControlPresenter
{
	private VideoPlayerView mDisplay;
	private Widget mCurrentVisibleWidget = null;
	
	public VideoPlayerPresenter(DataService dataService, HandlerManager eventBus, VideoPlayerView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
		bind();
	}
	
	private enum ShowPanel
	{
		Default, Video, Audio, Subtitles, DVD, User
	}
	
	private void bind()
	{
		mDisplay.getPlayerPad().setVisible(true);
		mDisplay.getVideoPanel().setVisible(false);     
		mDisplay.getAudioPanel().setVisible(false);
		mDisplay.getSubtitlesPanel().setVisible(false); 
		mDisplay.getUserPanel().setVisible(false);
		mDisplay.getDvdPanel().setVisible(false);
		onClickButton(ShowPanel.Default);
		
		mDisplay.getTopPanel().getBtnDefault().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onClickButton(ShowPanel.Default);
			}
		});
		
		mDisplay.getTopPanel().getBtnVideoSettings().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onClickButton(ShowPanel.Video);
			}
		});
		
		mDisplay.getTopPanel().getBtnAudioSettings().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onClickButton(ShowPanel.Audio);
			}
		});
		
		mDisplay.getTopPanel().getBtnSubtitlesSettings().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onClickButton(ShowPanel.Subtitles);
			}
		});
		
		mDisplay.getTopPanel().getBtnDVDMenu().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onClickButton(ShowPanel.DVD);
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
	}
	
	protected void onClickButton(ShowPanel what)
	{
		if(mCurrentVisibleWidget != null)
			mCurrentVisibleWidget.setVisible(false);
		mCurrentVisibleWidget = null;
		switch(what)
		{
			case Default: mCurrentVisibleWidget =   mDisplay.getPlayerPad();break;
			case Video:   mCurrentVisibleWidget =   mDisplay.getVideoPanel();break;
			case Audio:   mCurrentVisibleWidget =   mDisplay.getAudioPanel();break;
			case Subtitles: mCurrentVisibleWidget = mDisplay.getSubtitlesPanel();break;
			case DVD :    mCurrentVisibleWidget =   mDisplay.getDvdPanel();break;
			case User: mCurrentVisibleWidget =      mDisplay.getUserPanel();break;			
		}
		
		if(mCurrentVisibleWidget != null)
			mCurrentVisibleWidget.setVisible(true);
	}
	
	private String translateCommand(String command)
	{
		String result = command;
		if (command.equals(JoyPad.COMMAND_LEFT))
			result = "DVDMenuLeft";
		else if (command.equals(JoyPad.COMMAND_RIGHT))
			result = "DVDMenuRight";
		else if (command.equals(JoyPad.COMMAND_UP))
			result = "DVDMenuUp";
		else if (command.equals(JoyPad.COMMAND_DOWN))
			result = "DVDMenuDown";
		else if (command.equals(JoyPad.COMMAND_CENTER))
			result = "DVDMenuEnter";
		return result;
	}


	@Override
	protected Command getCommand(String command)
	{
		VideoPlayerCommand vpc =  new VideoPlayerCommand(RemoteControl.VideoPlayer);
		vpc.Method = translateCommand(command);
		if(command.equals("Start"))
		{
			ListBox lb = mDisplay.getTopPanel().getCmbItems();
			if(lb.getSelectedIndex() > -1)
			{
				String v = lb.getValue(lb.getSelectedIndex()); 
				if(v != null && v.trim().length() != 0)
					vpc.MethodParameter = v;
			}
		}
		return vpc;
	}

	@Override
	public String getName()
	{
		return "VideoPlayer";
	}
}

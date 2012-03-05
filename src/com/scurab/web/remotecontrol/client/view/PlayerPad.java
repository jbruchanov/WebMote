package com.scurab.web.remotecontrol.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.InlineLabel;
import com.scurab.web.remotecontrol.client.components.CommandableClickHandlerWrapper;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class PlayerPad extends AbstractView
{

	public final static String COMMAND_SEEK_BCK = "SeekBackward";
	public final static String COMMAND_PLAY = "Play";
	public final static String COMMAND_SEEK_FWD = "SeekForward";
	public final static String COMMAND_PREVIOUS = "Previous";
	public final static String COMMAND_PAUSE = "Pause";
	public final static String COMMAND_STOP = "Stop";
	public final static String COMMAND_NEXT = "Next";
	public final static String COMMAND_VOLUME_DOWN = "VolumeDown";
	public final static String COMMAND_VOLUME_MUTE = "Mute";
	public final static String COMMAND_VOLUME_UP = "VolumeUp";
	
	
	private static PlayerPadUiBinder uiBinder = GWT.create(PlayerPadUiBinder.class);
	@UiField InlineLabel btnMinus;
	@UiField InlineLabel btnPlus;
	@UiField InlineLabel btnMute;
	@UiField InlineLabel btnStop;
	@UiField InlineLabel btnPause;
	@UiField InlineLabel btnPlay;
	@UiField InlineLabel btnSeekBack;
	@UiField InlineLabel btnPrevious;
	@UiField InlineLabel btnSeekForward;
	@UiField InlineLabel btnNext;
	
	CommandableClickHandlerWrapper cmdMinus = null;
	CommandableClickHandlerWrapper cmdPlus = null;
	CommandableClickHandlerWrapper cmdMute = null;
	CommandableClickHandlerWrapper cmdStop = null;
	CommandableClickHandlerWrapper cmdPause = null;
	CommandableClickHandlerWrapper cmdPlay = null;
	CommandableClickHandlerWrapper cmdSeekBack = null;
	CommandableClickHandlerWrapper cmdPrev = null;
	CommandableClickHandlerWrapper cmdSeekFwd = null;
	CommandableClickHandlerWrapper cmdNext = null;
	List<IsCommandableClickHandler> mList = null;

	interface PlayerPadUiBinder extends UiBinder<Widget, PlayerPad>
	{
	}

	public PlayerPad()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		cmdMinus = CommandableClickHandlerWrapper.asCommandableClickObject(btnMinus, COMMAND_VOLUME_DOWN);  
		cmdPlus = CommandableClickHandlerWrapper.asCommandableClickObject(btnPlus, COMMAND_VOLUME_UP);  
		cmdMute = CommandableClickHandlerWrapper.asCommandableClickObject(btnMinus, COMMAND_VOLUME_MUTE);  
		
		cmdStop = CommandableClickHandlerWrapper.asCommandableClickObject(btnStop, COMMAND_STOP);  
		cmdPause = CommandableClickHandlerWrapper.asCommandableClickObject(btnPause, COMMAND_PAUSE);  
		cmdPlay = CommandableClickHandlerWrapper.asCommandableClickObject(btnPlay, COMMAND_PLAY);  
		
		cmdSeekBack = CommandableClickHandlerWrapper.asCommandableClickObject(btnSeekBack, COMMAND_SEEK_BCK);  
		cmdPrev = CommandableClickHandlerWrapper.asCommandableClickObject(btnPrevious, COMMAND_PREVIOUS);  
		cmdSeekFwd = CommandableClickHandlerWrapper.asCommandableClickObject(btnSeekForward, COMMAND_SEEK_FWD);  
		cmdNext = CommandableClickHandlerWrapper.asCommandableClickObject(btnNext, COMMAND_NEXT);  
		
		mList = new ArrayList<IsCommandableClickHandler>();
		mList.addAll(Arrays.asList(new IsCommandableClickHandler[]{
				cmdMinus,cmdPlus,cmdMute,cmdStop,cmdPause,cmdPlay,cmdSeekBack,cmdPrev,cmdSeekFwd,cmdNext
		}));
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return mList;
	}

}

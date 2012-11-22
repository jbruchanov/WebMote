package com.scurab.web.remotecontrol.client.presenter;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.R;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.commands.ApplicationCommand;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.InfoCommand;
import com.scurab.web.remotecontrol.client.commands.ApplicationCommand.AppType;
import com.scurab.web.remotecontrol.client.event.ChangePresenterEvent;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.tools.JsonSimpleParser;
import com.scurab.web.remotecontrol.client.view.AudioPlayerView;
import com.scurab.web.remotecontrol.client.view.ConfigView;
import com.scurab.web.remotecontrol.client.view.JoyPad;
import com.scurab.web.remotecontrol.client.view.KeyboardView;
import com.scurab.web.remotecontrol.client.view.MediaCenterView;
import com.scurab.web.remotecontrol.client.view.PicturesView;
import com.scurab.web.remotecontrol.client.view.TVView;
import com.scurab.web.remotecontrol.client.view.VideoPlayerView;

public class MediaCenterPresenter extends BaseControlPresenter
{
	private MediaCenterView mDisplay = null;
	private Widget mCurrentVisibleWidget = null;
	private enum ShowPanel
	{
		Default, Spec, Keyboard, User
	}
	
	public MediaCenterPresenter(DataService dataService, HandlerManager eventBus, MediaCenterView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
		bind();
	}
	
	public void bind()
	{
		mDisplay.getSpecializedContainer().setVisible(false);
		mDisplay.getUserPanel().setVisible(false);
		
		
		mDisplay.getTopPanel().getBtnDefault().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onClickButton(ShowPanel.Default);}});
		mDisplay.getTopPanel().getBtnSpecializedActivity().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onClickButton(ShowPanel.Spec);}});
		mDisplay.getTopPanel().getBtnKeyboard().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onClickButton(ShowPanel.Keyboard);}});
		mDisplay.getTopPanel().getBtnUser().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){mEventBus.fireEvent(new ChangePresenterEvent(new ConfigPresenter(mDataService, mEventBus, new ConfigView())));}});
		
		onClickButton(ShowPanel.Default);
	}
	
	protected void onClickButton(ShowPanel what)
	{
		if(mCurrentVisibleWidget != null)
			mCurrentVisibleWidget.setVisible(false);
		mCurrentVisibleWidget = null;
		switch(what)
		{
			case Default: mCurrentVisibleWidget =   mDisplay.getJoyPad();break;
			case Spec: mCurrentVisibleWidget =    mDisplay.getSpecializedContainer();break;
			case Keyboard: 
			{
				mEventBus.fireEvent(new ChangePresenterEvent(new KeyboardPresenter(mDataService, mEventBus, new KeyboardView())));
				return;
			}
			case User: mCurrentVisibleWidget =      mDisplay.getUserPanel();break;			
		}
		
		if(mCurrentVisibleWidget != null)
			mCurrentVisibleWidget.setVisible(true);
	}
	
	@Override
	protected void onSendCommand(Command command)
	{
		
		super.onSendCommand(command);
		if(R.MediaCenter.OPEN_AUDIO.equals(command.getMethod()) 
				|| R.MediaCenter.OPEN_PICTURE.equals(command.getMethod())
				|| R.MediaCenter.OPEN_TELEVISION.equals(command.getMethod())
				|| R.MediaCenter.OPEN_VIDEO.equals(command.getMethod()))
		{
			boolean open = mDisplay.getChkRunSpecActivity().getValue();
			if(open)
				handleOpeningSpecActivity(translateCommandMethod(command.getMethod()));
		}
	}
	
	private String translateCommandMethod(String cmd)
	{
		if(R.MediaCenter.OPEN_AUDIO.equals(cmd))
			return RemoteControl.PropertyKeys.AUDIOPLAYER;
		else if(R.MediaCenter.OPEN_PICTURE.equals(cmd))
			return RemoteControl.PropertyKeys.PICTURESVIEWER;
		else if(R.MediaCenter.OPEN_TELEVISION.equals(cmd))				
			return RemoteControl.PropertyKeys.TVAPPLIATION;
		else if(R.MediaCenter.OPEN_VIDEO.equals(cmd))				
			return RemoteControl.PropertyKeys.VIDEOPLAYER;
		else
			return null;
	}
	
	private void handleOpeningSpecActivity(final String command)
	{
		try
		{
		mDataService.sendCommand(new InfoCommand(), new RequestCallback()
		{
			@Override
			public void onResponseReceived(Request request, Response response)
			{
				try
				{
					InfoCommand ic = JsonSimpleParser.parseInfoCommand(response.getText());
					HashMap<String, List<String>> data = ic.getApplications();
					onOpenSpecializedActivity(command,data.get(command));
				}
				catch(Exception e)				
				{
					Window.alert(e.getMessage());
				}
			}
			
			@Override
			public void onError(Request request, Throwable exception)
			{
				Window.alert(exception.getMessage());
			}
		});
		}
		catch(Exception e)
		{
			Window.alert(e.getMessage());
		}
	}
	
	public void onOpenSpecializedActivity(String cmd, List<String> apps)
	{
		
		BaseControlPresenter bcp = null;
		String mceName = getApplication(RemoteControl.MediaCenter,apps);
		if(RemoteControl.PropertyKeys.AUDIOPLAYER.equals(cmd))
			bcp = new AudioPlayerPresenter(mDataService, mEventBus, new AudioPlayerView());
		else if(RemoteControl.PropertyKeys.VIDEOPLAYER.equals(cmd))
			bcp = new VideoPlayerPresenter(mDataService, mEventBus, new VideoPlayerView());
		else if(RemoteControl.PropertyKeys.TVAPPLIATION.equals(cmd))				
			bcp = new TVPresenter(mDataService, mEventBus, new TVView());
		else if(RemoteControl.PropertyKeys.PICTURESVIEWER.equals(cmd))				
			bcp = new PicturesPresenter(mDataService, mEventBus, new PicturesView());
		
		if(bcp != null)
		{
			bcp.setApplication(mceName);
			ChangePresenterEvent cpe = new ChangePresenterEvent(bcp);		
			mEventBus.fireEvent(cpe);
		}
	}
	
	private String getApplication(String mediaCenterName, List<String> apps)
	{
		for(String app : apps)
		{
			if(app.startsWith(mediaCenterName))
				return app;
		}
		return null;
	}
	
	@Override
	protected Command getCommand(String command)
	{
		ApplicationCommand mcc = new ApplicationCommand(RemoteControl.MediaCenter, AppType.MediaCenter);
		mcc.setMethod(translateCommand(command));
		return mcc;
	}
	
	private String translateCommand(String command)
	{
		String result = command;
		if (command.equals(JoyPad.COMMAND_CENTER))
			result = "Enter";
		return result;
	}

	@Override
	public String getName()
	{
		return "MediaCenterView";
	}

}

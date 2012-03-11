package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.R;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.MediaCenterCommand;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.JoyPad;
import com.scurab.web.remotecontrol.client.view.MediaCenterView;

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
		mDisplay.getTopPanel().getBtnUser().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onClickButton(ShowPanel.User);}});
		
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
//			case Keyboard: mCurrentVisibleWidget =    mDisplay.getOptionsPanel();break;
			case User: mCurrentVisibleWidget =      mDisplay.getUserPanel();break;			
		}
		
		if(mCurrentVisibleWidget != null)
			mCurrentVisibleWidget.setVisible(true);
	}
	
	@Override
	protected void onSendCommand(Command command)
	{
		
		super.onSendCommand(command);
		if(R.MediaCenter.OPEN_AUDIO.equals(command.Method) 
				|| R.MediaCenter.OPEN_PICTURE.equals(command.Method)
				|| R.MediaCenter.OPEN_TELEVISION.equals(command.Method)
				|| R.MediaCenter.OPEN_VIDEO.equals(command.Method))
		{
			boolean open = mDisplay.getChkRunSpecActivity().getValue();
			if(open)
				onOpenSpecializedActivity(command.Method);
		}
	}
	
	public void onOpenSpecializedActivity(String cmd)
	{
		Window.alert(cmd);
	}
	
	@Override
	protected Command getCommand(String command)
	{
		MediaCenterCommand mcc = new MediaCenterCommand(RemoteControl.MediaCenter);
		mcc.Method = translateCommand(command);
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

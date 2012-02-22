package com.scurab.web.remotecontrol.client.controls;

import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.scurab.web.remotecontrol.client.interfaces.Commandable;

public class MobileButton extends Button implements Commandable
{
	private String mCommand;
	private String preStyle = "";
	public MobileButton()
	{
		setStyleName("rc-Button");
		/*getElement().setAttribute("style", "margin:3px");*/
		
		addTouchStartHandler(new TouchStartHandler()
		{
			
			@Override
			public void onTouchStart(TouchStartEvent event)
			{			
				onStartTouch();
			}
		});
		
		addTouchEndHandler(new TouchEndHandler()
		{
			
			@Override
			public void onTouchEnd(TouchEndEvent event)
			{
				onEndTouch();
			}
		});
	}
	
	public void onStartTouch()
	{
		preStyle = getStyleName();
		setStyleName("rc-Button-Active");
	}
	
	public void onEndTouch()
	{
		setStyleName("rc-Button");
	}
	
	@Override
	public void setCommand(String command)
	{
		mCommand = command;
	}

	@Override
	public String getCommand()
	{
		return mCommand;
	}
}

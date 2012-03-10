package com.scurab.web.remotecontrol.client.controls;

import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class CommandButton extends Button implements IsCommandableClickHandler
{
	private String mCommand;
	
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

package com.scurab.web.remotecontrol.client.commands;

public class MediaCenterCommand extends ApplicationCommand
{

	public MediaCenterCommand(String appName)
	{
		super(appName);
	}

	@Override
	public String getCommand()
	{
		return "MediaCenterCommand";
	}

}

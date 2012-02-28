package com.scurab.web.remotecontrol.client.commands;

public class MediaCenterCommand extends ApplicationCommand
{

	public MediaCenterCommand(String appName)
	{
		super(appName);
	}

	@Override
	protected String getCommandName()
	{
		return "MediaCenterCommand";
	}

}

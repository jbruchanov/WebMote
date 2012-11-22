package com.scurab.web.remotecontrol.client.commands;

public class PhotoViewerCommand extends ApplicationCommand
{

	public PhotoViewerCommand(String appName)
	{
		super(appName);
	}

	@Override
	public String getCommand()
	{
		return "PhotoViewerCommand";
	}

}

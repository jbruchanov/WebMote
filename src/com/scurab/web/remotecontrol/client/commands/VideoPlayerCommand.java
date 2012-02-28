package com.scurab.web.remotecontrol.client.commands;

public class VideoPlayerCommand extends ApplicationCommand
{

	public VideoPlayerCommand(String appName)
	{
		super(appName);
	}

	@Override
	protected String getCommandName()
	{
		return "VideoPlayerCommand";
	}

}

package com.scurab.web.remotecontrol.client.commands;

public class AudioPlayerCommand extends ApplicationCommand
{

	public AudioPlayerCommand(String appName)
	{
		super(appName);
	}

	@Override
	public String getCommand()
	{
		return "AudioPlayerCommand";
	}
}

package com.scurab.web.remotecontrol.client.commands;

public class TelevisionCommand extends ApplicationCommand
{

	public TelevisionCommand(String appName)
	{
		super(appName);
	}

	@Override
	public String getCommand()
	{
		return "TelevisionCommand";
	}

}

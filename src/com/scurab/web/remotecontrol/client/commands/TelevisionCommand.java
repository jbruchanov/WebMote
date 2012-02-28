package com.scurab.web.remotecontrol.client.commands;

public class TelevisionCommand extends ApplicationCommand
{

	public TelevisionCommand(String appName)
	{
		super(appName);
	}

	@Override
	protected String getCommandName()
	{
		return "TelevisionCommand";
	}

}

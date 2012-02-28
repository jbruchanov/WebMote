package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;

public class VolumeCommand extends Command
{
	public int volume = 0;
	private static final String VOLUME_KEY = "volume";
	

	public JSONObject getJsonObject()
	{
		JSONObject jso = super.getJsonObject();
		jso.put(VOLUME_KEY, new JSONNumber(volume));
		return jso;
	}
	
	@Override 
	public String toString()
	{
		return getJsonObject().toString();
	}

	@Override
	protected String getCommandName()
	{
		return "VolumeCommand";
	}
}

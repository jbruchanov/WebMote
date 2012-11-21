package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;

public class VolumeCommand extends Command
{
	private int mVolume = 0;
	private static final String VOLUME_KEY = "Value";
	
	public VolumeCommand()
	{
		setMethod(GET);
	}

	@Override
	public JSONObject getJsonObject()
	{
		JSONObject jso = super.getJsonObject();
		jso.put(VOLUME_KEY, new JSONNumber(getVolume()));
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

	public int getVolume()
	{
		return mVolume;
	}

	public void setVolume(int volume)
	{
		setMethod(SET);
		this.mVolume = volume;
	}
}

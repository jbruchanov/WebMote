package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public abstract class Command
{
	private static final String PIN_KEY = "IMEI";
	protected String PIN = "0000";
	private static final String COMMAND_KEY = "Command";
	
	public String Method = null;
	private static final String METHOD_KEY = "Method";
	
	protected JSONObject getJsonObject()
	{		
		JSONObject jso = new JSONObject();
		jso.put(PIN_KEY, new JSONString(PIN));		
		jso.put(COMMAND_KEY, new JSONString(getCommandName()));
		if(Method != null)
			jso.put(METHOD_KEY, new JSONString(Method));
		return jso;
	}
	
	@Override
	public String toString()
	{	
		return getJsonObject().toString();
	}
	
	protected abstract String getCommandName();
}

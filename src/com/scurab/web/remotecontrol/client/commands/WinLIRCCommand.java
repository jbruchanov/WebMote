package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class WinLIRCCommand extends ApplicationCommand
{

	

	public String RemoteControl = null;	
	public final static String RemoteControl_KEY = "RemoteControl";
	
	public WinLIRCCommand(String appName)
	{
		super(appName);
		// TODO Auto-generated constructor stub
	}
	
	public JSONObject getJsonObject()
	{
		JSONObject jso = super.getJsonObject();
		jso.put(RemoteControl_KEY, new JSONString(RemoteControl));
		return jso;
	}
	
	public void GetAvailableCodes()
	{
		Method = "GetCodes";
	}
	
	@Override
	protected String getCommandName()
	{
		return "WinLIRCCommand";
	}

}

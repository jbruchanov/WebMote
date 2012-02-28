package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class KeyboardCommand extends Command
{

	public String KeyCode = "";
	public final static String KEYCODE_KEY = "KeyCode";
	
	protected JSONObject getJsonObject()
	{		
		JSONObject jso = super.getJsonObject();
		jso.put(KEYCODE_KEY, new JSONString(KeyCode));					
		return jso;
	}
	
	@Override
	protected String getCommandName()
	{
		return "KeyboardCommand";
	}

}

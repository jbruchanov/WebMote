package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class KeyboardCommand extends Command
{
	private String mKeyCode = "";
	public final static String KEYCODE_KEY = "KeyCodes";
	
	@Override
	protected JSONObject getJsonObject()
	{		
		JSONObject jso = super.getJsonObject();
		jso.put(KEYCODE_KEY, new JSONString(getKeyCode()));					
		return jso;
	}
	
	@Override
	protected String getCommand()
	{
		return "KeyboardCommand";
	}

	public String getKeyCode()
	{
		return mKeyCode;
	}

	public void setKeyCode(String keyCode)
	{
		mKeyCode = keyCode;
	}

}

package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;

public class MouseCommand extends Command
{

	public int X;
	private final static String X_KEY = "X";
	public int Y;
	private final static String Y_KEY = "Y";
	
	@Override
	protected JSONObject getJsonObject()
	{		
		JSONObject jso = super.getJsonObject();
		jso.put(X_KEY, new JSONNumber(X));		
		jso.put(Y_KEY, new JSONNumber(Y));		
		return jso;
	}
	
	@Override
	protected String getCommand()
	{
		return "MouseCommand";
	}

}

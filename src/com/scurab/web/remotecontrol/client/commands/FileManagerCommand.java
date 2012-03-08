package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class FileManagerCommand extends Command
{

	public String Root = null;
	private final static String ROOT_KEY = "Root";
	public String Filter = null;
	private final static String FILTER_KEY = "Filter";
	
	@Override
	protected JSONObject getJsonObject()
	{		
		JSONObject jso = super.getJsonObject();
		if(Root != null)
			jso.put(ROOT_KEY, new JSONString(Root));
		if(Filter != null)
			jso.put(FILTER_KEY, new JSONString(Filter));		
		return jso;
	}
	
	@Override
	protected String getCommandName()
	{
		return "FileManagerCommand";
	}

}

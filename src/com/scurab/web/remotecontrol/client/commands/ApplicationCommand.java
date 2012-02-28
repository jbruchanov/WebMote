package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public abstract class ApplicationCommand extends Command
{

	public String Application = null;
	public String MethodParameter = null;
	
	private static final String APP_KEY = "Application";
	private static final String METHODMAPARM_KEY = "MethodParameter";
	
	public ApplicationCommand(String appName)
	{
		super();
		Application = appName;
	}
	
	protected JSONObject getJsonObject()
	{		
		JSONObject jso = super.getJsonObject();
		jso.put(APP_KEY, new JSONString(Application));		
		jso.put(METHODMAPARM_KEY, new JSONString(MethodParameter));
		return jso;
	}
}

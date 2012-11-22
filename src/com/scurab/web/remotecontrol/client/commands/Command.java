package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.scurab.web.remotecontrol.client.RemoteControl;

public abstract class Command
{
	private static final String PIN_KEY = "PIN";
	private static final String COMMAND_KEY = "Command";
	protected static final String METHOD_KEY = "Method";
	private static final String PROTOCOL_KEY = "ProtocolVersion";
	private static final String PLATFORM_KEY = "SourcePlatform";
	
	private String mMethod;
	private int mProtocolVersion;
	
	public static final String GET = "get";
    public static final String SET = "set";

	protected JSONObject getJsonObject()
	{		
		JSONObject jso = new JSONObject();
		jso.put(PIN_KEY, new JSONString(RemoteControl.getPIN()));		
		jso.put(COMMAND_KEY, new JSONString(getCommand()));
		if(mMethod != null){
			jso.put(METHOD_KEY, new JSONString(mMethod));
		}
		jso.put(PROTOCOL_KEY, new JSONNumber(getProtocolVersion()));
		jso.put(PLATFORM_KEY, new JSONString("Web"));
		if(mMethod != null){
			jso.put(METHOD_KEY, new JSONString(getMethod()));
		}		
		return jso;
	}
	
	
	@Override
	public String toString()
	{	
		return getJsonObject().toString();
	}
	
	protected abstract String getCommand();

	public int getProtocolVersion()
	{
		return mProtocolVersion;
	}

	public void setProtocolVersion(int protocolVersion)
	{
		mProtocolVersion = protocolVersion;
	}


	public String getMethod()
	{
		return mMethod;
	}


	public void setMethod(String method)
	{
		mMethod = method;
	}
}

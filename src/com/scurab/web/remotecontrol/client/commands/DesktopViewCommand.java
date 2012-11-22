package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;

public class DesktopViewCommand extends Command
{

	public int X;
	private final static String X_KEY = "X";
	public int Y;
	private final static String Y_KEY = "Y";
	public int Width;
	private final static String WIDTH_KEY = "Width";
	public int Height;
	private final static String HEIGHT_KEY = "Height";
	public int Compress = 20;
	private final static String COMPRESS_KEY = "Compress";
	public float Scale = 1f;
	private final static String SCALE_KEY = "Scale";
	
	
	
	@Override
	protected JSONObject getJsonObject()
	{		
		JSONObject jso = super.getJsonObject();
		jso.put(X_KEY, new JSONNumber(X));		
		jso.put(Y_KEY, new JSONNumber(Y));
		jso.put(WIDTH_KEY, new JSONNumber(Width));
		jso.put(HEIGHT_KEY, new JSONNumber(Height));
		jso.put(COMPRESS_KEY, new JSONNumber(Compress));
		jso.put(SCALE_KEY, new JSONNumber(Scale));
		return jso;
	}
	
	@Override
	protected String getCommand()
	{
		return "DesktopViewCommand";
	}

}

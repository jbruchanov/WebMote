package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;

public class MouseCommand extends Command
{

	private int mX;
	private final static String X_KEY = "XX";
	private int mY;
	private final static String Y_KEY = "DY";
	private int mScroll;
	private final static String SCROLL_KEY = "Scroll";

	@Override
	protected JSONObject getJsonObject()
	{
		JSONObject jso = super.getJsonObject();
		jso.put(X_KEY, new JSONNumber(mX));
		jso.put(Y_KEY, new JSONNumber(mY));
		jso.put(SCROLL_KEY, new JSONNumber(mScroll));
		return jso;
	}

	@Override
	protected String getCommand()
	{
		return "MouseCommand";
	}

	public int getX()
	{
		return mX;
	}

	public void setX(int x)
	{
		mX = x;
	}

	public int getY()
	{
		return mY;
	}

	public void setY(int y)
	{
		mY = y;
	}

}

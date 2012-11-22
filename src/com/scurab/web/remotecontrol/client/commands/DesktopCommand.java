package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;

public class DesktopCommand extends Command
{

	private int mX;
	private final static String X_KEY = "X";
	private int mY;
	private final static String Y_KEY = "Y";
	private int mWidth;
	private final static String WIDTH_KEY = "Width";
	private int mHeight;
	private final static String HEIGHT_KEY = "Height";
	private int mCompress = 20;
	private final static String COMPRESS_KEY = "Compress";
	private float mScale = 1f;
	private final static String SCALE_KEY = "Scale";
	
	
	
	@Override
	protected JSONObject getJsonObject()
	{		
		JSONObject jso = super.getJsonObject();
		jso.put(X_KEY, new JSONNumber(getX()));		
		jso.put(Y_KEY, new JSONNumber(getY()));
		jso.put(WIDTH_KEY, new JSONNumber(getWidth()));
		jso.put(HEIGHT_KEY, new JSONNumber(getHeight()));
		jso.put(COMPRESS_KEY, new JSONNumber(getCompress()));
		jso.put(SCALE_KEY, new JSONNumber(getScale()));
		return jso;
	}
	
	@Override
	protected String getCommand()
	{
		return "DesktopViewCommand";
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

	public int getWidth()
	{
		return mWidth;
	}

	public void setWidth(int width)
	{
		mWidth = width;
	}

	public int getHeight()
	{
		return mHeight;
	}

	public void setHeight(int height)
	{
		mHeight = height;
	}

	public int getCompress()
	{
		return mCompress;
	}

	public void setCompress(int compress)
	{
		mCompress = compress;
	}

	public float getScale()
	{
		return mScale;
	}

	public void setScale(float scale)
	{
		mScale = scale;
	}

}

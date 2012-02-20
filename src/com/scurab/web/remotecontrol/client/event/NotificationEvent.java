package com.scurab.web.remotecontrol.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class NotificationEvent extends GwtEvent<NotificationEventHandler>
{
	public static Type<NotificationEventHandler> TYPE = new Type<NotificationEventHandler>();
	
	private String mMessage = null;
	private int mType;
	private boolean mShowProgressBar = false;
	
	public static final int TYPE_NEUTRAL = 0;
	public static final int TYPE_OK = 1;
	public static final int TYPE_WARNING = 2;
	public static final int TYPE_ERROR = 3;
	
	public NotificationEvent(String msg)
	{
		this(msg, 0, false);
	}
	
	public NotificationEvent(String msg, boolean showProgressBar)
	{
		this(msg,0,showProgressBar);
	}
	
	public NotificationEvent(String msg, int type)
	{
		this(msg,type,false);
	}
	
	public NotificationEvent(String msg, int type, boolean showProgressBar)
	{
		mMessage = msg;
		mType = type;
		mShowProgressBar = showProgressBar;
	}
	
	@Override
	public Type<NotificationEventHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch(NotificationEventHandler handler)
	{
		handler.onNotification(this);
	}
	
	public String getMessage()
	{
		return mMessage;
	}
	
	public int getType()
	{
		return mType;
	}
	
	public boolean getShowProgressBar()
	{
		return mShowProgressBar;
	}
}

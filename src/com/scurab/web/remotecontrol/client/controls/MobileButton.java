package com.scurab.web.remotecontrol.client.controls;

import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.Timer;

public class MobileButton extends CommandButton
{
	private String mDefaultStyleName;
	private String mActiveStyleName;
	private static Timer sTimer = null;
	
	public MobileButton()
	{
		sTimer = new Timer()
		{
			@Override
			public void run()
			{		
				onEndTouch();
			}
		};
		
		getElement().setAttribute("style", "margin:3px;min-height:45px");
		
		addTouchStartHandler(new TouchStartHandler()
		{
			
			@Override
			public void onTouchStart(TouchStartEvent event)
			{			
//				onStartTouch();
			}
		});
		
		addTouchEndHandler(new TouchEndHandler()
		{
			
			@Override
			public void onTouchEnd(TouchEndEvent event)
			{
//				onEndTouch();
			}
		});
	}
	
	@Override
	protected void onAttach()
	{
		super.onAttach();
//		setDefaultStyle();
	}
	
	protected void setDefaultStyle()
	{
		if(mDefaultStyleName == null)
			setStyleName("rc-Button");
		else
			setStyleName(mDefaultStyleName);
	}
	
	protected void setActiveStyle()
	{
		if(mActiveStyleName == null)
			setStyleName("rc-Button-Active");
		else
			setStyleName(mActiveStyleName);
	}
	
	public void onStartTouch()
	{
		setActiveStyle();
		sTimer.cancel();
		sTimer.schedule(1000);
	}
	
	public void onEndTouch()
	{
		setDefaultStyle();
	}
	
	public String getDefaultStyleName()
	{
		return mDefaultStyleName;
	}

	public void setDefaultStyleName(String styleName)
	{
		mDefaultStyleName = styleName;
	}

	public String getActiveStyleName()
	{
		return mActiveStyleName;
	}

	public void setActiveStyleName(String activeStyleName)
	{
		mActiveStyleName = activeStyleName;
	}
}

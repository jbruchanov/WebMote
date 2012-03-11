package com.scurab.web.remotecontrol.client.controls;

import com.google.gwt.user.client.ui.Button;

public class KeyButton extends Button
{
	String mKeyCode = null;

	public String getKeyCode()
	{
		return mKeyCode;
	}

	public void setKeyCode(String keyCode)
	{
		mKeyCode = keyCode;
	}
	
}

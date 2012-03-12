package com.scurab.web.remotecontrol.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.RootPanel;
import com.scurab.web.remotecontrol.client.view.RootView;
import com.scurab.web.remotecontrol.language.Words;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RemoteControl implements EntryPoint
{
	public static String TVAppliation = "Avermedia TV";
	public static String VideoPlayer = "VLC Player";
	public static String AudioPlayer = "WinAmp";
	public static String PicturesViewer = "Windows Photo Viewer";
	public static String MediaCenter = "Media Center";
	public static String IRDevice = "logitech_z680";
	
	public static final Words Words = GWT.create(Words.class);
	
	
	@Override
	public void onModuleLoad()
	{
		initDefaultApps();
		RootPanel.get("content").add(new RootView());
	}
	
	private void initDefaultApps()
	{	
		TVAppliation = getProperty(PropertyKeys.TVAPPLIATION);
		VideoPlayer = getProperty(PropertyKeys.VIDEOPLAYER);
		AudioPlayer = getProperty(PropertyKeys.AUDIOPLAYER);
		PicturesViewer = getProperty(PropertyKeys.PICTURESVIEWER);
		MediaCenter = getProperty(PropertyKeys.MEDIACENTER);
		IRDevice = getProperty(PropertyKeys.IRDEVICE);
	}

	public static String getPIN()
	{
		String p = getProperty(PropertyKeys.PIN); 
		if(p == null || p.length() == 0)
			p = "0000";
		return p;
	}
	
	public static void setProperty(String key, String value)
	{
		Cookies.setCookie(key, value);
	}
	
	public static String getProperty(String key)
	{
		return Cookies.getCookie(key);
	}
	
	public static String getProperty(String key, String defaultValue)
	{
		String s = Cookies.getCookie(key);
		if(s == null)
			s = defaultValue;
		return s;
	}
	
	public static class PropertyKeys 
	{
		public static final String TVAPPLIATION = "Television";
		public static final String VIDEOPLAYER = "Video";
		public static final String AUDIOPLAYER = "Audio";
		public static final String PICTURESVIEWER = "Picture";
		public static final String MEDIACENTER = "MediaCenter";
		public static final String IRDEVICE = "WinLIRC";
		public static final String PIN = "PIN";
		public static final String FIRST_START = "FIRST_START";
	}
}

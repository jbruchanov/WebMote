package com.scurab.web.remotecontrol.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.RootPanel;
import com.scurab.web.remotecontrol.client.datamodel.KeyValueItem;
import com.scurab.web.remotecontrol.client.view.RootView;
import com.scurab.web.remotecontrol.language.Words;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RemoteControl implements EntryPoint
{
	public static String TVAppliation = null;
	public static String VideoPlayer = null;
	public static String AudioPlayer = null;
	public static String PicturesViewer = null;
	public static String MediaCenter = null;
	public static String IRDevice = null;
	
	public static final Words Words = GWT.create(Words.class);
	
	
	@Override
	public void onModuleLoad()
	{
		initDefaultApps();		
		RootPanel.get("content").add(new RootView());
	}
	
	public static native String getUserAgent() /*-{
	return navigator.userAgent.toLowerCase();
	}-*/;
	
	private static void initDefaultApps()
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
		initDefaultApps();
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
	
	public static void addToFavorites(String category, String key, String location)
	{
		Storage s = Storage.getLocalStorageIfSupported();
		String value = s.getItem(category);
		if(value != null && value.length() > 0)
			value = value + R.Constants.ITEM_SEPARATOR;
		else
			value = "";
		
		value = value + (key + R.Constants.VALUE_SEPARATOR + location);
		s.setItem(category, value);
	}	
	
	public static List<KeyValueItem> getFavorities(String category)
	{
		List<KeyValueItem> result = new ArrayList<KeyValueItem>();
		Storage s = Storage.getLocalStorageIfSupported();
		String data = s.getItem(category);
		if(data != null && data.length() > 0)
		{
			String[] rows = data.split(R.Constants.ITEM_SEPARATOR);
			for(String row : rows)
			{
				String[] d = row.split(R.Constants.VALUE_SEPARATOR);
				KeyValueItem ki = new KeyValueItem();
				ki.Key = d[0];
				ki.Value = d[1];				
				result.add(ki);
			}
		}
		return result;
	}
	
	public static class PropertyKeys 
	{
		public static final String TVAPPLIATION = "Television";
		public static final String VIDEOPLAYER = "Video";
		public static final String AUDIOPLAYER = "Audio";
		public static final String PICTURESVIEWER = "Picture";
		public static final String MEDIACENTER = "MediaCenter";
		public static final String IRDEVICE = "InfraRed";
		public static final String PIN = "PIN";
		public static final String FIRST_START = "FIRST_START";
		
		public static final String[] ALL_APP_PROPERTY_KEYS = new String[] {RemoteControl.PropertyKeys.AUDIOPLAYER,
			RemoteControl.PropertyKeys.MEDIACENTER,RemoteControl.PropertyKeys.PICTURESVIEWER,
			RemoteControl.PropertyKeys.TVAPPLIATION,RemoteControl.PropertyKeys.VIDEOPLAYER,RemoteControl.PropertyKeys.IRDEVICE};
	}
}

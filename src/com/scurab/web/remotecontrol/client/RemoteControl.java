package com.scurab.web.remotecontrol.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.scurab.web.remotecontrol.client.view.RootView;
import com.scurab.web.remotecontrol.language.Words;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RemoteControl implements EntryPoint
{
	public static final String TVAppliation = "Avermedia TV";
	public static final String VideoPlayer = "VLC Player";
	public static final String AudioPlayer = "WinAmp";
	public static final String PicturesViewer = "Windows Photo Viewer";
	public static final String MediaCenter = "Media Center";
	
	public static final Words Words = GWT.create(Words.class);
	
	
	@Override
	public void onModuleLoad()
	{
		RootPanel.get("content").add(new RootView());
	}
}

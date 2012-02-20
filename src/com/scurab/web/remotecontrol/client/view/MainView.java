package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.controls.ImageButton;

public class MainView extends Composite
{
	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	@UiField ImageButton btnTV;
	@UiField ImageButton btnAudio;
	@UiField ImageButton btnVideo;
	@UiField ImageButton btnPictures;
	@UiField ImageButton btnVolume;
	@UiField ImageButton btnFileBrowser;
	@UiField ImageButton btnMediaCenter;
	@UiField ImageButton btnNext;
	interface MainViewUiBinder extends UiBinder<Widget, MainView>
	{
	}

	public MainView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}	
	
	
	public ImageButton getTVButton()
	{
		return btnTV;
	}
	public ImageButton getAudioButton()
	{
		return btnAudio;
	}
	public ImageButton getVideoButton()
	{
		return btnVideo;
	}
	public ImageButton getPicturesButton()
	{
		return btnPictures;
	}
	public ImageButton getVolumeButton()
	{
		return btnVolume;
	}
	public ImageButton getFileBrowserButton()
	{
		return btnFileBrowser;
	}
	public ImageButton getMediaCenterButton()
	{
		return btnMediaCenter;
	}
	public ImageButton getNextButton()
	{
		return btnNext;
	}
}

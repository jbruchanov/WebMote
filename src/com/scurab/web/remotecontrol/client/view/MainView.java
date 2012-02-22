package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.controls.ImageMobileButton;

public class MainView extends Composite
{
	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	@UiField ImageMobileButton btnTV;
	@UiField ImageMobileButton btnAudio;
	@UiField ImageMobileButton btnVideo;
	@UiField ImageMobileButton btnPictures;
	@UiField ImageMobileButton btnVolume;
	@UiField ImageMobileButton btnFileBrowser;
	@UiField ImageMobileButton btnMediaCenter;
	@UiField ImageMobileButton btnNext;
	interface MainViewUiBinder extends UiBinder<Widget, MainView>
	{
	}

	public MainView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}	
	
	
	public ImageMobileButton getTVButton()
	{
		return btnTV;
	}
	public ImageMobileButton getAudioButton()
	{
		return btnAudio;
	}
	public ImageMobileButton getVideoButton()
	{
		return btnVideo;
	}
	public ImageMobileButton getPicturesButton()
	{
		return btnPictures;
	}
	public ImageMobileButton getVolumeButton()
	{
		return btnVolume;
	}
	public ImageMobileButton getFileBrowserButton()
	{
		return btnFileBrowser;
	}
	public ImageMobileButton getMediaCenterButton()
	{
		return btnMediaCenter;
	}
	public ImageMobileButton getNextButton()
	{
		return btnNext;
	}
}

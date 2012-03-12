package com.scurab.web.remotecontrol.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.ListBox;
import com.scurab.web.remotecontrol.client.controls.ImageMobileButton;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class VideoPlayerTopPanel extends AbstractView
{

	private static MediaPlayerTopPanelUiBinder uiBinder = GWT.create(MediaPlayerTopPanelUiBinder.class);
	@UiField ListBox cmbItems;
	@UiField ImageMobileButton btnQuit;
	@UiField ImageMobileButton btnStart;
	@UiField ImageMobileButton btnDefault;
	@UiField ImageMobileButton btnVideoSettings;
	@UiField ImageMobileButton btnAudioSettings;
	@UiField ImageMobileButton btnSubtitlesSettings;
	@UiField ImageMobileButton btnDVDMenu;
	@UiField ImageMobileButton btnUser;

	private List<IsCommandableClickHandler> mButtons = null;
	interface MediaPlayerTopPanelUiBinder extends UiBinder<Widget, VideoPlayerTopPanel>
	{
	}

	public VideoPlayerTopPanel()
	{
		initWidget(uiBinder.createAndBindUi(this));
		mButtons = new ArrayList<IsCommandableClickHandler>();
		mButtons.addAll(Arrays.asList(new IsCommandableClickHandler[] {btnQuit,btnStart}));
	}

	public ListBox getCmbItems()
	{
		return cmbItems;
	}

	public void setCmbItems(ListBox cmbItems)
	{
		this.cmbItems = cmbItems;
	}

	public ImageMobileButton getBtnDefault()
	{
		return btnDefault;
	}

	public ImageMobileButton getBtnVideoSettings()
	{
		return btnVideoSettings;
	}

	public ImageMobileButton getBtnAudioSettings()
	{
		return btnAudioSettings;
	}

	public ImageMobileButton getBtnSubtitlesSettings()
	{
		return btnSubtitlesSettings;
	}

	public ImageMobileButton getBtnDVDMenu()
	{
		return btnDVDMenu;
	}

	public ImageMobileButton getBtnUser()
	{
		return btnUser;
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return mButtons;
	}
}

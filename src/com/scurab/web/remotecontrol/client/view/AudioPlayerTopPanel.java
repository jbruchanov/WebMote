package com.scurab.web.remotecontrol.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.scurab.web.remotecontrol.client.controls.ImageMobileButton;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;
import com.google.gwt.user.client.ui.ListBox;

public class AudioPlayerTopPanel extends AbstractView
{

	private static AudioPlayerTopPanelUiBinder uiBinder = GWT.create(AudioPlayerTopPanelUiBinder.class);
	@UiField ImageMobileButton btnDefault;
	@UiField ImageMobileButton btnOptions;
	@UiField ImageMobileButton btnUser;
	@UiField ImageMobileButton btnQuit;
	@UiField ImageMobileButton btnStart;
	@UiField ListBox cmbItems;

	interface AudioPlayerTopPanelUiBinder extends UiBinder<Widget, AudioPlayerTopPanel>
	{
	}

	private List<IsCommandableClickHandler> mButtons = null;
	public AudioPlayerTopPanel()
	{
		initWidget(uiBinder.createAndBindUi(this));
		mButtons = new ArrayList<IsCommandableClickHandler>();
		mButtons.addAll(Arrays.asList(new IsCommandableClickHandler[] {btnQuit,btnStart}));
		
		cmbItems.addItem("", "");
		cmbItems.addItem("Progressive", "http://listen.di.fm/public2/progressive.pls");
	}
	
	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return mButtons;
	}

	public ImageMobileButton getBtnDefault()
	{
		return btnDefault;
	}

	public void setBtnDefault(ImageMobileButton btnDefault)
	{
		this.btnDefault = btnDefault;
	}

	public ImageMobileButton getBtnOptions()
	{
		return btnOptions;
	}

	public void setBtnOptions(ImageMobileButton btnOptions)
	{
		this.btnOptions = btnOptions;
	}

	public ImageMobileButton getBtnUser()
	{
		return btnUser;
	}

	public void setBtnUser(ImageMobileButton btnUser)
	{
		this.btnUser = btnUser;
	}

	public ListBox getCmbItems()
	{
		return cmbItems;
	}
}

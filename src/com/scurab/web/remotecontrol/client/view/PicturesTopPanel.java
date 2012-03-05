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

public class PicturesTopPanel extends AbstractView
{

	private static PicturesTopPanelUiBinder uiBinder = GWT.create(PicturesTopPanelUiBinder.class);
	@UiField ImageMobileButton btnQuit;
	@UiField ImageMobileButton btnStart;
	@UiField ImageMobileButton btnDefault;
	@UiField ImageMobileButton btnOptions;
	@UiField ImageMobileButton btnUser;
	@UiField ListBox cmbItems;

	private List<IsCommandableClickHandler> mButtons = null;
	
	interface PicturesTopPanelUiBinder extends UiBinder<Widget, PicturesTopPanel>
	{
	}

	public PicturesTopPanel()
	{
		initWidget(uiBinder.createAndBindUi(this));
		mButtons = new ArrayList<IsCommandableClickHandler>();
		mButtons.addAll(Arrays.asList(new IsCommandableClickHandler[] {btnQuit, btnStart}));
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

	public ImageMobileButton getBtnOptions()
	{
		return btnOptions;
	}

	public ImageMobileButton getBtnUser()
	{
		return btnUser;
	}

	public ListBox getCmbItems()
	{
		return cmbItems;
	}
}

package com.scurab.web.remotecontrol.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;
import com.scurab.web.remotecontrol.client.controls.ImageMobileButton;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Image;

public class DiskBrowserView extends AbstractView
{

	private static DiskBrowserViewUiBinder uiBinder = GWT.create(DiskBrowserViewUiBinder.class);
	@UiField TextBox txtFilter;
	@UiField ToggleButton tglFilter;
	@UiField HTMLPanel contentPanel;
	@UiField ImageMobileButton btnCustomLocation;
	@UiField Label lblCurrentLocation;
	@UiField Image imgLoader;

	interface DiskBrowserViewUiBinder extends UiBinder<Widget, DiskBrowserView>
	{
	}

	public DiskBrowserView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	public TextBox getFilterTextBox()
	{
		return txtFilter;
	}

	public ToggleButton getFilterToggleButton()
	{
		return tglFilter;
	}

	public HTMLPanel getContentPanel()
	{
		return contentPanel;
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return null;
	}

	public TextBox getTxtFilter()
	{
		return txtFilter;
	}

	public ToggleButton getTglFilter()
	{
		return tglFilter;
	}

	public ImageMobileButton getBtnCustomLocation()
	{
		return btnCustomLocation;
	}

	public Label getLblCurrentLocation()
	{
		return lblCurrentLocation;
	}
	
	public void setProgressVisible(boolean value)
	{
		imgLoader.setVisible(value);
	}
}

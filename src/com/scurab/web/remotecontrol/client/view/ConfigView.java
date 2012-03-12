package com.scurab.web.remotecontrol.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Button;

public class ConfigView extends AbstractView
{

	private static ConfigViewUiBinder uiBinder = GWT.create(ConfigViewUiBinder.class);
	@UiField TextBox txtPIN;
	@UiField ListBox cmbTV;
	@UiField ListBox cmbVideoPlayer;
	@UiField ListBox cmbAudioPlayer;
	@UiField ListBox cmbPictureViewer;
	@UiField ListBox cmbMediaCenter;
	@UiField ListBox cmbIRDevices;
	@UiField Button btnSave;
	@UiField Button btnFavorites;

	interface ConfigViewUiBinder extends UiBinder<Widget, ConfigView>
	{
	}

	public ConfigView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return null;
	}

	public TextBox getTxtPIN()
	{
		return txtPIN;
	}

	public ListBox getCmbTV()
	{
		return cmbTV;
	}

	public ListBox getCmbVideoPlayer()
	{
		return cmbVideoPlayer;
	}

	public ListBox getCmbAudioPlayer()
	{
		return cmbAudioPlayer;
	}

	public ListBox getCmbPictureViewer()
	{
		return cmbPictureViewer;
	}

	public ListBox getCmbMediaCenter()
	{
		return cmbMediaCenter;
	}

	public ListBox getCmbIRDevices()
	{
		return cmbIRDevices;
	}

	public Button getBtnSave()
	{
		return btnSave;
	}

	public Button getBtnFavorites()
	{
		return btnFavorites;
	}
}

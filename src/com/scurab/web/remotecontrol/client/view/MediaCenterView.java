package com.scurab.web.remotecontrol.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.scurab.web.remotecontrol.client.controls.ImageMobileButton;

public class MediaCenterView extends AbstractView
{

	private static MediaCenterViewUiBinder uiBinder = GWT.create(MediaCenterViewUiBinder.class);
	@UiField MediaCenterTopPanel topPanel;
	@UiField JoyPad joyPad;
	@UiField CheckBox chkRunSpecActivity;
	@UiField VerticalPanel specializedContainer;
	@UiField VerticalPanel userPanel;
	@UiField ImageMobileButton btnTV;
	@UiField ImageMobileButton btnVideo;
	@UiField ImageMobileButton btnAudio;
	@UiField ImageMobileButton btnPictures;
	
	private List<IsCommandableClickHandler> mButtons = null;

	interface MediaCenterViewUiBinder extends UiBinder<Widget, MediaCenterView>
	{
	}

	public MediaCenterView()
	{
		initWidget(uiBinder.createAndBindUi(this));
		mButtons = new ArrayList<IsCommandableClickHandler>();
		mButtons.addAll(topPanel.getClickElements());
		mButtons.addAll(joyPad.getClickElements());
		mButtons.addAll(Arrays.asList(new IsCommandableClickHandler[] {btnTV,btnAudio,btnPictures,btnVideo}));
	}

	public MediaCenterView(String firstName)
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return mButtons;
	}

	public MediaCenterTopPanel getTopPanel()
	{
		return topPanel;
	}

	public void setTopPanel(MediaCenterTopPanel topPanel)
	{
		this.topPanel = topPanel;
	}

	public JoyPad getJoyPad()
	{
		return joyPad;
	}

	public VerticalPanel getSpecializedContainer()
	{
		return specializedContainer;
	}

	public VerticalPanel getUserPanel()
	{
		return userPanel;
	}

	public CheckBox getChkRunSpecActivity()
	{
		return chkRunSpecActivity;
	}

}

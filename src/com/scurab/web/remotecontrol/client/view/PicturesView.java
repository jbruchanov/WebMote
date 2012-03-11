package com.scurab.web.remotecontrol.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.scurab.web.remotecontrol.client.controls.MobileButton;

public class PicturesView extends AbstractView
{

	private static PictiuresViewUiBinder uiBinder = GWT.create(PictiuresViewUiBinder.class);
	@UiField PicturesTopPanel topPanel;
	@UiField VerticalPanel optionsPanel;
	@UiField JoyPad joyPad;
	@UiField MobileButton btnStart;
	@UiField MobileButton btnPause;
	@UiField MobileButton btnZoomIn;
	@UiField MobileButton btnZoomOut;
	@UiField MobileButton btnRotateLeft;
	@UiField MobileButton btnRotateRight;
	@UiField VerticalPanel userPanel;
	private List<IsCommandableClickHandler> mButtons = null;
	
	interface PictiuresViewUiBinder extends UiBinder<Widget, PicturesView>
	{
	}

	public PicturesView()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		mButtons = new ArrayList<IsCommandableClickHandler>();
		mButtons.addAll(getTopPanel().getClickElements());
		mButtons.addAll(Arrays.asList(
				new IsCommandableClickHandler[] { btnStart, btnPause,btnRotateLeft,btnRotateRight,btnZoomIn,btnZoomOut}));
		mButtons.addAll(joyPad.getClickElements());
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return mButtons;
	}

	public PicturesTopPanel getTopPanel()
	{
		return topPanel;
	}

	public VerticalPanel getOptionsPanel()
	{
		return optionsPanel;
	}

	public JoyPad getJoyPad()
	{
		return joyPad;
	}

	public VerticalPanel getUserPanel()
	{
		return userPanel;
	}

	public void setJoyPad(JoyPad joyPad)
	{
		this.joyPad = joyPad;
	}

}

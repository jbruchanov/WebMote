package com.scurab.web.remotecontrol.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.scurab.web.remotecontrol.client.controls.CommandButton;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Image;

public class DesktopView extends AbstractView
{

	private static DesktopViewUiBinder uiBinder = GWT.create(DesktopViewUiBinder.class);

	interface DesktopViewUiBinder extends UiBinder<Widget, DesktopView>
	{
	}
	@UiField HorizontalPanel topPanel;
	@UiField HTMLPanel container;
	@UiField ToggleButton tglOnOff;
	@UiField CommandButton btnLeft;
	@UiField CommandButton btnEscape;
	@UiField CommandButton btnRight;
	@UiField Image image;
	
	private List<IsCommandableClickHandler> mList = null;
	
	public DesktopView()
	{
		initWidget(uiBinder.createAndBindUi(this));
		mList = new ArrayList<IsCommandableClickHandler>();
		mList.addAll(Arrays.asList(new IsCommandableClickHandler[] {btnLeft,btnRight,btnEscape}));
	}
	
	
	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return mList;
	}

	public static DesktopViewUiBinder getUiBinder()
	{
		return uiBinder;
	}

	public static void setUiBinder(DesktopViewUiBinder uiBinder)
	{
		DesktopView.uiBinder = uiBinder;
	}

	public HTMLPanel getContainer()
	{
		return container;
	}

	public ToggleButton getTglOnOff()
	{
		return tglOnOff;
	}

	public Button getBtnLeft()
	{
		return btnLeft;
	}

	public Button getBtnEscape()
	{
		return btnEscape;
	}

	public Button getBtnRight()
	{
		return btnRight;
	}

	public Image getImage()
	{
		return image;
	}

	public HorizontalPanel getTopPanel()
	{
		return topPanel;
	}
}

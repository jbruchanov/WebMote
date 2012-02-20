package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;

public class DiskBrowserView extends Composite
{

	private static DiskBrowserViewUiBinder uiBinder = GWT.create(DiskBrowserViewUiBinder.class);
	@UiField TextBox txtFilter;
	@UiField ToggleButton tglFilter;
	@UiField HTMLPanel contentPanel;

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
}

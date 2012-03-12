package com.scurab.web.remotecontrol.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlowPanel;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class IRDeviceView extends AbstractView
{

	private static IRDeviceUiBinder uiBinder = GWT.create(IRDeviceUiBinder.class);

	interface IRDeviceUiBinder extends UiBinder<Widget, IRDeviceView>
	{
	}

	public IRDeviceView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField Label lblName;
	@UiField FlowPanel container;
	
	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return null;
	}

	public Label getLblName()
	{
		return lblName;
	}

	public FlowPanel getContainer()
	{
		return container;
	}
}

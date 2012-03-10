package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FocusPanel;
import com.scurab.web.remotecontrol.client.datamodel.Proc;

public class ProcessItem extends Composite implements HasClickHandlers
{

	private static ProcessItemUiBinder uiBinder = GWT.create(ProcessItemUiBinder.class);

	interface ProcessItemUiBinder extends UiBinder<Widget, ProcessItem>
	{
	}
	
	@UiField Label lblProcessName;
	@UiField Label lblProcessId;
	@UiField Label lblProcessDescription;
	@UiField FocusPanel container;
	private Proc mProcess;

	public ProcessItem()
	{
		initWidget(uiBinder.createAndBindUi(this));
		lblProcessDescription.setVisible(false);
	}
	
	public ProcessItem(Proc p)
	{
		this();
		mProcess = p;
		lblProcessName.setText(p.Name);
		lblProcessId.setText(String.valueOf(p.ID));
		if(p.Description != null && p.Description.length() > 0)
		{
			lblProcessDescription.setVisible(true);
			lblProcessDescription.setText(p.Description);
		}		
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler)
	{
		return container.addClickHandler(handler);
	}

	public Proc getProcess()
	{
		return mProcess;
	}
}

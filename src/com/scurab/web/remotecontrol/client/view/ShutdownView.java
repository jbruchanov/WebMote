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
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.CheckBox;
import com.scurab.web.remotecontrol.client.controls.CommandButton;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class ShutdownView extends AbstractView
{

	private static ShutdownViewUiBinder uiBinder = GWT.create(ShutdownViewUiBinder.class);
	@UiField Label lblOperationStartTime;
	@UiField CheckBox chkForce;
	@UiField TimePickerWidget timePicker;
	@UiField CommandButton btnReboot;
	@UiField CommandButton btnShutdown;
	@UiField CommandButton btnHibernate;
	@UiField CommandButton btnAbort;
	
	private ArrayList<IsCommandableClickHandler> mList;

	interface ShutdownViewUiBinder extends UiBinder<Widget, ShutdownView>
	{
	}

	public ShutdownView()
	{
		initWidget(uiBinder.createAndBindUi(this));
		mList = new ArrayList<IsCommandableClickHandler>();
		mList.addAll(Arrays.asList(new IsCommandableClickHandler[] {btnReboot,btnShutdown,btnHibernate,btnAbort}));
	}


	public ShutdownView(String firstName)
	{
		initWidget(uiBinder.createAndBindUi(this));
	}


	public Label getLblOperationStartTime()
	{
		return lblOperationStartTime;
	}


	public void setLblOperationStartTime(Label lblOperationStartTime)
	{
		this.lblOperationStartTime = lblOperationStartTime;
	}


	public CheckBox getChkForce()
	{
		return chkForce;
	}


	public TimePickerWidget getTimePicker()
	{
		return timePicker;
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return mList;
	}
}

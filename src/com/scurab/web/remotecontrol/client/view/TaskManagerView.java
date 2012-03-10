package com.scurab.web.remotecontrol.client.view;

import java.util.ArrayList;
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
import com.scurab.web.remotecontrol.client.controls.ImageMobileButton;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;

public class TaskManagerView extends AbstractView
{

	private static ProcessesViewUiBinder uiBinder = GWT.create(ProcessesViewUiBinder.class);

	interface ProcessesViewUiBinder extends UiBinder<Widget, TaskManagerView>
	{
	}

	public TaskManagerView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField ImageMobileButton btnStartCustomProcess;
	@UiField ImageMobileButton btnReload;
	@UiField HTMLPanel contentPanel;
	@UiField Image imgLoader;

	public TaskManagerView(String firstName)
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return new ArrayList<IsCommandableClickHandler>();
	}

	public ImageMobileButton getBtnStartCustomProcess()
	{
		return btnStartCustomProcess;
	}

	public ImageMobileButton getBtnReload()
	{
		return btnReload;
	}

	public HTMLPanel getContentPanel()
	{
		return contentPanel;
	}
	
	public void setProgressVisible(boolean value)
	{
		imgLoader.setVisible(value);
	}
}

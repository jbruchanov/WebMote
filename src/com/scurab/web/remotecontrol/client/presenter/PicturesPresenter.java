package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.PhotoViewerCommand;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.JoyPad;
import com.scurab.web.remotecontrol.client.view.PicturesView;

public class PicturesPresenter extends BaseControlPresenter
{
	private PicturesView mDisplay = null;
	private Widget mCurrentVisibleWidget;
	
	private enum ShowPanel
	{
		Default, Options, User
	}
	
	public PicturesPresenter(DataService dataService, HandlerManager eventBus, PicturesView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
		bind();
	}
	
	private void bind()
	{
		onClickButton(ShowPanel.Default);
		mDisplay.getOptionsPanel().setVisible(false);
		mDisplay.getUserPanel().setVisible(false);
		
		mDisplay.getTopPanel().getBtnDefault().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onClickButton(ShowPanel.Default);
			}
		});
		
		mDisplay.getTopPanel().getBtnOptions().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onClickButton(ShowPanel.Options);
			}
		});
		
		mDisplay.getTopPanel().getBtnUser().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onClickButton(ShowPanel.User);
			}
		});
	}
	
	protected void onClickButton(ShowPanel what)
	{
		if(mCurrentVisibleWidget != null)
			mCurrentVisibleWidget.setVisible(false);
		mCurrentVisibleWidget = null;
		switch(what)
		{
			case Default: mCurrentVisibleWidget =   mDisplay.getJoyPad();break;
			case Options: mCurrentVisibleWidget =    mDisplay.getOptionsPanel();break;
			case User: mCurrentVisibleWidget =      mDisplay.getUserPanel();break;			
		}
		
		if(mCurrentVisibleWidget != null)
			mCurrentVisibleWidget.setVisible(true);
	}
	
	
	@Override
	protected Command getCommand(String command)
	{
		PhotoViewerCommand pvc = new PhotoViewerCommand("Windows Photo Viewer");
		pvc.Method = translateCommand(command);
		if(command.equals("Start"))
		{
			ListBox lb = mDisplay.getTopPanel().getCmbItems();
			if(lb.getSelectedIndex() > -1)
			{
				String v = lb.getValue(lb.getSelectedIndex()); 
				if(v != null && v.trim().length() != 0)
					pvc.MethodParameter = v;
			}
		}
		return pvc;
	}
	
	private String translateCommand(String command)
	{
		String result = command;
		if (command.equals(JoyPad.COMMAND_LEFT))
			result = "Previous";
		else if (command.equals(JoyPad.COMMAND_RIGHT))
			result = "Next";		
		return result;
	}


}

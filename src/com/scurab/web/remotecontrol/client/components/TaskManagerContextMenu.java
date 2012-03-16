package com.scurab.web.remotecontrol.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.controls.MobileButton;
import com.scurab.web.remotecontrol.client.datamodel.Proc;

public class TaskManagerContextMenu extends DialogBox
{
	public interface OnClickListener
	{
		void onClick(Proc item, ContextType contextCommand);
	}
	public enum ContextType
	{
		Activate, Kill, Cancel
	}

	
	private TaskManagerContextMenu(Proc item, OnClickListener listener)
	{		
		setModal(true);		
		setGlassEnabled(true);
		setWidget(buildWidget(item, listener));
		setWidth("70%");
	}
	
	private Widget buildWidget(Proc item, OnClickListener listener)
	{
		return buildFolderWidget(item, listener);	
	}
	
	private Widget buildFolderWidget(final Proc item, final OnClickListener listener)
	{
		FlowPanel fp = new FlowPanel();
		fp.add(new Label(item.Name));
		fp.add(getButton(item,listener,ContextType.Activate));
		fp.add(getButton(item,listener,ContextType.Kill));		
		fp.add(getButton(item,listener,ContextType.Cancel));
		return fp;
	}
	
	private MobileButton getButton(final Proc item, final OnClickListener listener, final ContextType type)
	{
		MobileButton mb = new MobileButton();		
		mb.setCommand(type.toString());
		mb.setText(type.toString());
		mb.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				TaskManagerContextMenu.this.hide();
				if(type != ContextType.Cancel)
					listener.onClick(item, type);					
			}
		});
		mb.setWidth("100%");
		return mb;
	}
	public static void showDialog(Proc item, OnClickListener listener)
	{
		TaskManagerContextMenu fbcm = new TaskManagerContextMenu(item, listener);		
		fbcm.show();
		fbcm.center();
	}
}

package com.scurab.web.remotecontrol.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.controls.MobileButton;

public class MonitorDialog extends DialogBox
{
	public interface OnClickListener
	{
		void onClick(ContextType contextCommand);
	}
	public enum ContextType
	{
		On, Off, Cancel
	}

	
	private MonitorDialog(OnClickListener listener)
	{		
		setModal(true);		
		setGlassEnabled(true);
		setWidget(buildWidget(listener));
		setWidth("70%");
	}
	
	private Widget buildWidget(OnClickListener listener)
	{
		return buildFolderWidget(listener);	
	}
	
	private Widget buildFolderWidget(final OnClickListener listener)
	{
		FlowPanel fp = new FlowPanel();		
		fp.add(getButton(listener,ContextType.On));
		fp.add(getButton(listener,ContextType.Off));		
		fp.add(getButton(listener,ContextType.Cancel));
		return fp;
	}
	
	private MobileButton getButton(final OnClickListener listener, final ContextType type)
	{
		MobileButton mb = new MobileButton();		
		mb.setCommand(type.toString());
		mb.setText(RemoteControl.Words.getString(type.toString()));
		mb.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				MonitorDialog.this.hide();
				if(type != ContextType.Cancel)
					listener.onClick(type);					
			}
		});
		mb.setWidth("100%");
		return mb;
	}
	public static void showDialog(OnClickListener listener)
	{
		MonitorDialog fbcm = new MonitorDialog(listener);		
		fbcm.show();
		fbcm.center();
	}
}

package com.scurab.web.remotecontrol.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.controls.MobileButton;
import com.scurab.web.remotecontrol.client.view.DiskBrowserItem;
import com.scurab.web.remotecontrol.client.view.DiskBrowserItem.Type;

public class FileBrowserContextMenu extends DialogBox
{
	public interface OnClickListener
	{
		void onClick(DiskBrowserItem item, ContextType contextCommand);
	}
	public enum ContextType
	{
		AudioPlayer, PicturesViewer, VideoPlayer, StartByOS, AddToFavorites, Cancel,
		Open, Close
	}

	
	private FileBrowserContextMenu(DiskBrowserItem item, OnClickListener listener)
	{
		setTitle("Open in...");		
		setModal(true);		
		setGlassEnabled(true);
		setWidget(buildWidget(item, listener));
		setWidth("70%");
	}
	
	private Widget buildWidget(DiskBrowserItem item, OnClickListener listener)
	{
		return buildFolderWidget(item, listener);	
	}
	
	private Widget buildFolderWidget(final DiskBrowserItem item, final OnClickListener listener)
	{
		FlowPanel fp = new FlowPanel();
		fp.getElement().setAttribute("style", "backgroud:#000;");
		fp.add(getButton(item,listener,ContextType.AudioPlayer));
		fp.add(getButton(item,listener,ContextType.PicturesViewer));
		fp.add(getButton(item,listener,ContextType.VideoPlayer));
		fp.add(getButton(item,listener,ContextType.StartByOS));
		//fp.add(getButton(item,listener,ContextType.AddToFavorites));
		if(item.getType() == Type.OpticalDevice)
		{
			fp.add(getButton(item,listener,ContextType.Open));
			fp.add(getButton(item,listener,ContextType.Close));
		}
		fp.add(getButton(item,listener,ContextType.Cancel));
		return fp;
	}
	
	private MobileButton getButton(final DiskBrowserItem item, final OnClickListener listener, final ContextType type)
	{
		MobileButton mb = new MobileButton();		
		mb.setCommand(type.toString());
		mb.setText(type.toString());
		mb.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				FileBrowserContextMenu.this.hide();
				if(type != ContextType.Cancel)
					listener.onClick(item, type);					
			}
		});
		mb.setWidth("100%");
		return mb;
	}
	public static void showDialog(DiskBrowserItem item, OnClickListener listener)
	{
		FileBrowserContextMenu fbcm = new FileBrowserContextMenu(item, listener);		
		fbcm.show();
		fbcm.center();
	}
}

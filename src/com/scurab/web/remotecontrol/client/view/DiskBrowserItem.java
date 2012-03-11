package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.scurab.web.remotecontrol.client.controls.ImageMobileButton;
import com.google.gwt.user.client.ui.Button;

public class DiskBrowserItem extends Composite implements HasClickHandlers
{
	public interface OnContextButtonClickListener
	{
		void onClick(Type t, String value);
	}
	
	private static DiskBrowserItemUiBinder uiBinder = GWT.create(DiskBrowserItemUiBinder.class);
	
	@UiField ImageMobileButton btnContext;
	@UiField Button btnItem;
	
	private Type mType = null;
	private OnContextButtonClickListener mOnContextButtonClickListener = null;
	
	
	public enum Type
	{		
		Return(-1), Folder(10), File(20), Disk(1), OpticalDevice(2), UsbDevice(3), Network(4), Ram(5);
		private int code;
		
		 private Type(int c) {
		   code = c;
		 }
		 
		 public int getCode() {
		   return code;
		 }
		 
		 public static Type valueOf(int v)
		 {
			 switch(v)
			 {
			 	case -1:return Return;
			 	case 10:return Folder;
			 	case 20:return File;
			 	case 1:return Disk;
			 	case 2:return OpticalDevice;
			 	case 3:return UsbDevice;
			 	case 4:return Network;
			 	case 5:return Ram;
			 	default: return null;			 		
			 }
		 }
	}	
	
	interface DiskBrowserItemUiBinder extends UiBinder<Widget, DiskBrowserItem>
	{
	}

	public DiskBrowserItem()
	{
		this(null, null);
	}
	
	public DiskBrowserItem(String type, String value)
	{
		initWidget(uiBinder.createAndBindUi(this));
		setType(type);
		btnItem.setText(value);		
		bind();
	}
	
	private void bind()
	{
		btnContext.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onContextButtonClick();
			}
		});
	}

	public Button getBtnItem()
	{
		return btnItem;
	}
	
	public void setType(String type)
	{		
		int t = Integer.parseInt(type);
		setType(Type.valueOf(t));
	}
	
	public void setType(Type t)
	{
		mType = t;
		btnContext.setUrl(getImage(t));
	}
	
	public String getValue()
	{
		return btnItem.getText();
	}
	
	public void onContextButtonClick()
	{
		if(mOnContextButtonClickListener != null)
			mOnContextButtonClickListener.onClick(mType, getValue());
	}

	public void setOnContextButtonClickListener(OnContextButtonClickListener onContextButtonClickListener)
	{
		mOnContextButtonClickListener = onContextButtonClickListener;
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler)
	{
		return btnItem.addClickHandler(handler);
	}
	
	public Type getType()
	{
		return mType;
	}
	
	private String getImage(Type t)
	{
		String img = null; 
		switch(t)
		{
			case Disk:
				img = "ic_filemanager_harddrive";
				break;
			case File:
				img = "ic_filemanager_file";
				break;
			case Folder:
				img = "ic_filemanager_folder";
				break;
			case OpticalDevice:
				img = "ic_filemanager_opticaldrive";
				break;
			case Return:
				img = "ic_filemanager_back";
				break;
			case UsbDevice:
				img = "ic_filemanager_removeabledrive";
				break;
		}
		
		return "pics/small/" + img + ".png";		
	}
}

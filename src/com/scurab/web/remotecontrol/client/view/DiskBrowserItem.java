package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DiskBrowserItem extends Composite
{

	private static DiskBrowserItemUiBinder uiBinder = GWT.create(DiskBrowserItemUiBinder.class);

	interface DiskBrowserItemUiBinder extends UiBinder<Widget, DiskBrowserItem>
	{
	}

	public DiskBrowserItem()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
}

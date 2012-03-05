package com.scurab.web.remotecontrol.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class PicturesView extends AbstractView
{

	private static PictiuresViewUiBinder uiBinder = GWT.create(PictiuresViewUiBinder.class);

	interface PictiuresViewUiBinder extends UiBinder<Widget, PicturesView>
	{
	}

	public PicturesView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		// TODO Auto-generated method stub
		return null;
	}
}

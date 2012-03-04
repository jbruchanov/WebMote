package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class VideoPlayerView extends Composite
{

	private static VideoPlayerViewUiBinder uiBinder = GWT.create(VideoPlayerViewUiBinder.class);

	interface VideoPlayerViewUiBinder extends UiBinder<Widget, VideoPlayerView>
	{
	}

	public VideoPlayerView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
}

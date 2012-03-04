package com.scurab.web.remotecontrol.client.presenter;


import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.scurab.web.remotecontrol.client.commands.VolumeCommand;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.VolumeControl;

public class VolumeControlPresenter extends BasePresenter
{
	private VolumeControl mDisplay = null;
	private int mLastSendVolume = -1;
	private long mLastSend = 0;
	
	public VolumeControlPresenter(DataService dataService, HandlerManager eventBus, VolumeControl display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
		bind();
	}
	
	private void bind()
	{
		mDisplay.addValueChangeHandler(new ValueChangeHandler<Integer>()
		{
			@Override
			public void onValueChange(ValueChangeEvent<Integer> event)
			{
				int volume = event.getValue();
				if(volume != mLastSendVolume)
				{
					mLastSendVolume = volume;
					onVolumeChange(volume);
				}
				
			}
		});
	}
	
	public void onVolumeChange(int i)
	{
		try
		{
			long now = System.currentTimeMillis();
			if(now > mLastSend + 250)//500ms
			{
				VolumeCommand vc = new VolumeCommand();
				vc.volume = i;
				mDataService.sendCommand(vc);
				mLastSend = now;
			}
		}
		catch(Exception e)
		{
			Window.alert(e.getMessage());
		}
	}

}

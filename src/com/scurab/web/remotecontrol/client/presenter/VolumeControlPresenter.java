package com.scurab.web.remotecontrol.client.presenter;


import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.VolumeCommand;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.VolumeControl;

public class VolumeControlPresenter extends BaseControlPresenter
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
		onSendCommand(String.valueOf(i));
	}
	
	@Override
	protected void onSendCommand(String command)
	{
		try
		{
			long now = System.currentTimeMillis();
			if(now > mLastSend + 250)//500ms
			{
				mDataService.sendCommand(getCommand(command));
				mLastSend = now;
			}
		}
		catch(Exception e)
		{
			Window.alert(e.getMessage());
		}
	}

	@Override
	protected Command getCommand(String command)
	{
		VolumeCommand vc = new VolumeCommand();
		vc.setVolume(Integer.parseInt(command));
		return vc;
	}

	@Override
	public String getName()
	{
		return "VolumeControl";
	}
}

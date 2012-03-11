package com.scurab.web.remotecontrol.client.presenter;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.SystemCommand;
import com.scurab.web.remotecontrol.client.components.Time;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.tools.StringUtils;
import com.scurab.web.remotecontrol.client.view.ShutdownView;
import com.scurab.web.remotecontrol.client.view.TimePickerWidget;

public class ShutdownPresenter extends BaseControlPresenter
{
	private ShutdownView mDisplay = null;
	
	public ShutdownPresenter(DataService dataService, HandlerManager eventBus, ShutdownView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
		bind();
		onTimeChange(new Time());
	}
	
	private void bind()
	{
		mDisplay.getTimePicker().addValueChangeHandler(new ValueChangeHandler<Time>()
		{
			@Override
			public void onValueChange(ValueChangeEvent<Time> event)
			{			
				onTimeChange(event.getValue());
			}
		});
	}
	
	protected void onTimeChange(Time time)
	{
		Date d = new Date(System.currentTimeMillis());
		int hour = (d.getHours() + time.hours + ((d.getMinutes() + time.minutes) / 60)) % 24;
		int minutes = (d.getMinutes() + time.minutes) % 60;
		mDisplay.getLblOperationStartTime().setText(StringUtils.get2DecimalValue(hour) + ":" + StringUtils.get2DecimalValue(minutes));
	}

	@Override
	public String getName()
	{
		return "ShutdownPresenter";
	}
	
	@Override
	protected void onSendCommand(String command)
	{
		if("Abort".equals(command) || Window.confirm(RemoteControl.Words.AreYouSure()))
			super.onSendCommand(command);
	}

	@Override
	protected Command getCommand(String command)
	{
		SystemCommand sc = new SystemCommand();
		sc.Operation = command;
		sc.Force = mDisplay.getChkForce().getValue();
		sc.Delay = getDelay();
		return sc;
	}
	
	protected int getDelay()
	{
		TimePickerWidget tpw = mDisplay.getTimePicker();
		return tpw.getHours() * 3600 + tpw.getMinutes() * 60;
	}
}

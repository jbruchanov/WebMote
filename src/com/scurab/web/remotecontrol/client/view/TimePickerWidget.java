package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import com.scurab.web.remotecontrol.client.components.Time;
import com.scurab.web.remotecontrol.client.tools.StringUtils;

public class TimePickerWidget extends Composite implements HasValue<Time>, HasValueChangeHandlers<Time>
{
	private HandlerManager mEventBus = null;
	private static TimePickerWidgetUiBinder uiBinder = GWT.create(TimePickerWidgetUiBinder.class);
	
	@UiField Label txtTime;
	@UiField Button btnAddHour;
	@UiField Button btnAddMinute;
	@UiField Button btnRemoveHour;
	@UiField Button btnRemoveMinute;
	
	private int mHours = 0;
	private int mMinutes = 0;

	interface TimePickerWidgetUiBinder extends UiBinder<Widget, TimePickerWidget>
	{
	}

	public TimePickerWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));
		mEventBus = new HandlerManager(this);
		bind();
	}
	
	private void bind()
	{
		btnAddHour.addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){incHour();}});
		btnAddMinute.addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){incMinute();}});
		btnRemoveHour.addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){decHour();}});
		btnRemoveMinute.addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){decMinute();}});
	}
	
	protected void incHour()
	{
		if(mHours < 23)
		{
			mHours++;
			refreshTime();
		}
	}
	
	protected void incMinute()
	{
		if(mMinutes < 59)
		{
			mMinutes++;
			refreshTime();
		}
	}
	
	protected void decHour()
	{
		if(mHours > 0)
		{
			mHours--;
			refreshTime();
		}
	}
	
	protected void decMinute()
	{
		if(mMinutes > 0)
		{
			mMinutes--;
			refreshTime();
		}
	}

	public int getHours()
	{
		return mHours;
	}

	public void setHours(int hours)
	{
		mHours = hours;
		refreshTime();
	}

	public int getMinutes()
	{
		return mMinutes;
	}

	public void setMinutes(int minutes)
	{
		mMinutes = minutes;
		refreshTime();
	}
	
	public void refreshTime()
	{
		setTimeText(mHours,mMinutes);
		fireEvent();
	}
	
	protected void setTimeText(int hours, int minutes)
	{
		String text = StringUtils.get2DecimalValue(hours) + ":" + StringUtils.get2DecimalValue(minutes);
		txtTime.setText(text);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Time> handler)
	{
		return mEventBus.addHandler(ValueChangeEvent.getType(), handler);
	}

	@Override
	public Time getValue()
	{
		Time t = new Time();
		t.hours = mHours;
		t.minutes = mMinutes;
		return t;
	}

	@Override
	public void setValue(Time value)
	{
		setValue(value, true);
	}

	@Override
	public void setValue(Time value, boolean fireEvents)
	{
		setTimeText(value.hours, value.minutes);
		if(fireEvents)
			fireEvent();
	}
	
	private void fireEvent()
	{		
		ValueChangeEvent.fire(this, new Time(mHours,mMinutes));
	}
	
	@Override
	public void fireEvent(GwtEvent<?> event)
	{
		mEventBus.fireEvent(event);
	}
}

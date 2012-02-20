package com.scurab.web.remotecontrol.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class DataLoadingEvent extends GwtEvent<DataLoadingEventHandler> {
	public static Type<DataLoadingEventHandler> TYPE = new Type<DataLoadingEventHandler>();
	public final static int START_LOADING = 1;
	public final static int STOP_LOADING = 2;
	private int mType = 0;

	public DataLoadingEvent(int type) {
		mType = type;
	}

	public int getType()
	{
		return mType;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DataLoadingEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DataLoadingEventHandler handler) {
		handler.onDataLoadingEvent(this);
	}

}

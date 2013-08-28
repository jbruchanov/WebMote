package com.scurab.web.remotecontrol.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ErrorEvent extends GwtEvent<ErrorEventHandler> {
    public static Type<ErrorEventHandler> TYPE = new Type<ErrorEventHandler>();
    private Throwable mException = null;

    public ErrorEvent(Throwable e) {
        mException = e;
    }

    public Throwable getException() {
        return mException;
    }

    @Override
    public Type<ErrorEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ErrorEventHandler handler) {
        handler.onError(this);
    }
}

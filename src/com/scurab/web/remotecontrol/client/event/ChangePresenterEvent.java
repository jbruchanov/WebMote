package com.scurab.web.remotecontrol.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.scurab.web.remotecontrol.client.presenter.BasePresenter;

public class ChangePresenterEvent extends GwtEvent<ChangePresenterEventHandler> {
    public static Type<ChangePresenterEventHandler> TYPE = new Type<ChangePresenterEventHandler>();

    private BasePresenter mBasePresenter = null;
    private String mApplication = null;

    public ChangePresenterEvent(BasePresenter newPresenter) {
        mBasePresenter = newPresenter;
    }

    public ChangePresenterEvent(BasePresenter newPresenter, String overrideApp) {
        mBasePresenter = newPresenter;
        mApplication = overrideApp;
    }

    public BasePresenter getPresenter() {
        return mBasePresenter;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ChangePresenterEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ChangePresenterEventHandler handler) {
        handler.onChangePresenter(this);
    }

    public String getApplication() {
        return mApplication;
    }
}
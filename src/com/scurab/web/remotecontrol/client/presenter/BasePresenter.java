package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.server.DataService;

public abstract class BasePresenter implements IsWidget {
    protected DataService mDataService = null;
    protected HandlerManager mEventBus = null;
    private IsWidget mDisplay = null;

    public BasePresenter(DataService dataService, HandlerManager eventBus,
            IsWidget display) {
        mDataService = dataService;
        mEventBus = eventBus;
        mDisplay = display;
    }

    protected void onPause() {

    }

    protected void onResume() {
    }

    @Override
    public Widget asWidget() {
        return mDisplay.asWidget();
    }

    protected void checkApp(String app) throws Exception {
        if (!(app != null && app.length() > 0)) {
            throw new Exception(RemoteControl.Words.ApplicationNotSet());
        }
    }

    public abstract String getName();
}

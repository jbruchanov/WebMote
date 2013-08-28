package com.scurab.web.remotecontrol.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface DataLoadingEventHandler extends EventHandler {
    void onDataLoadingEvent(DataLoadingEvent event);
}

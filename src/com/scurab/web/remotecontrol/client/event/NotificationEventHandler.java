package com.scurab.web.remotecontrol.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface NotificationEventHandler extends EventHandler {
    void onNotification(NotificationEvent event);
}

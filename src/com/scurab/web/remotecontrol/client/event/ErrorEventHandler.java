package com.scurab.web.remotecontrol.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ErrorEventHandler extends EventHandler {
	void onError(ErrorEvent event);
}

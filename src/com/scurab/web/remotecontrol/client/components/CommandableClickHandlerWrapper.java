package com.scurab.web.remotecontrol.client.components;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class CommandableClickHandlerWrapper implements
        IsCommandableClickHandler {
    private CommandableClickHandlerWrapper() {
    }

    HasClickHandlers mSource = null;
    String mCommand = null;

    public static CommandableClickHandlerWrapper asCommandableClickObject(
            HasClickHandlers i, String command) {
        CommandableClickHandlerWrapper c = new CommandableClickHandlerWrapper();
        c.mSource = i;
        c.mCommand = command;
        return c;
    }

    @Override
    public void setCommand(String command) {
        mCommand = command;
    }

    @Override
    public String getCommand() {
        return mCommand;
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return mSource.addClickHandler(handler);
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        mSource.fireEvent(event);
    }
}

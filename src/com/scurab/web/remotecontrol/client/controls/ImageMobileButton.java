package com.scurab.web.remotecontrol.client.controls;

import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.ui.Image;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class ImageMobileButton extends Image implements
        IsCommandableClickHandler {
    private String mCommand;

    public ImageMobileButton() {
        setStyleName("rc-ImageButton");
        /* getElement().setAttribute("style", "margin:3px"); */

        addTouchStartHandler(new TouchStartHandler() {

            @Override
            public void onTouchStart(TouchStartEvent event) {
                onStartTouch();
            }
        });

        addTouchEndHandler(new TouchEndHandler() {

            @Override
            public void onTouchEnd(TouchEndEvent event) {
                onEndTouch();
            }
        });

    }

    public void onStartTouch() {
        setStyleName("rc-ImageButton-Active");
    }

    public void onEndTouch() {
        setStyleName("rc-ImageButton");
    }

    @Override
    public void setCommand(String command) {
        mCommand = command;
    }

    @Override
    public String getCommand() {
        return mCommand;
    }
}

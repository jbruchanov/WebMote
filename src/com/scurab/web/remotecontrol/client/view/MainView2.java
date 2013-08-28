package com.scurab.web.remotecontrol.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.controls.ImageMobileButton;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class MainView2 extends AbstractView {
    private static MainView2UiBinder uiBinder = GWT
            .create(MainView2UiBinder.class);
    @UiField
    ImageMobileButton btnShutdown;
    @UiField
    ImageMobileButton btnTaskManager;
    @UiField
    ImageMobileButton btnRemoteDesktop;
    @UiField
    ImageMobileButton btnKeyboard;
    @UiField
    ImageMobileButton btnDisplay;
    @UiField
    ImageMobileButton btnIRDevices;
    @UiField
    ImageMobileButton btnJoyPad;
    @UiField
    ImageMobileButton btnMediaCenter;

    interface MainView2UiBinder extends UiBinder<Widget, MainView2> {
    }

    public MainView2() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public List<IsCommandableClickHandler> getClickElements() {
        return new ArrayList<IsCommandableClickHandler>();
    }

    public ImageMobileButton getShutdownButton() {
        return btnShutdown;
    }

    public ImageMobileButton getTaskManagerButton() {
        return btnTaskManager;
    }

    public ImageMobileButton getRemoteDesktopButton() {
        return btnRemoteDesktop;
    }

    public ImageMobileButton getKeyboardButton() {
        return btnKeyboard;
    }

    public ImageMobileButton getDisplayButton() {
        return btnDisplay;
    }

    public ImageMobileButton getMediaCenterButton() {
        return btnMediaCenter;
    }

    public ImageMobileButton getIRDevicesButton() {
        return btnIRDevices;
    }

    public ImageMobileButton getJoyPadButton() {
        return btnJoyPad;
    }
}

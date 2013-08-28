package com.scurab.web.remotecontrol.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.controls.ImageMobileButton;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class TVTopPanel extends AbstractView {

    private static TVTopPanelUiBinder uiBinder = GWT
            .create(TVTopPanelUiBinder.class);
    @UiField
    ImageMobileButton btnQuit;
    @UiField
    ImageMobileButton btnMute;
    @UiField
    ImageMobileButton btnFullScreen;
    @UiField
    ImageMobileButton btnPlay;
    @UiField
    ImageMobileButton btnDefault;
    @UiField
    ImageMobileButton btnNumeric;
    @UiField
    ImageMobileButton btnRecord;
    @UiField
    ImageMobileButton btnUser;

    private List<IsCommandableClickHandler> mButtons = null;

    interface TVTopPanelUiBinder extends UiBinder<Widget, TVTopPanel> {
    }

    public TVTopPanel() {
        initWidget(uiBinder.createAndBindUi(this));
        mButtons = new ArrayList<IsCommandableClickHandler>();
        mButtons.addAll(Arrays.asList(new IsCommandableClickHandler[] {
                btnQuit, btnMute, btnFullScreen, btnPlay }));
    }

    @Override
    public List<IsCommandableClickHandler> getClickElements() {
        return mButtons;
    }

    public ImageMobileButton getBtnDefault() {
        return btnDefault;
    }

    public ImageMobileButton getBtnNumeric() {
        return btnNumeric;
    }

    public ImageMobileButton getBtnRecord() {
        return btnRecord;
    }

    public ImageMobileButton getBtnUser() {
        return btnUser;
    }
}

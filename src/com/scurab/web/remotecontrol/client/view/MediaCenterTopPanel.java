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

public class MediaCenterTopPanel extends AbstractView {

    private static MediaCenterTopPanelUiBinder uiBinder = GWT
            .create(MediaCenterTopPanelUiBinder.class);

    interface MediaCenterTopPanelUiBinder extends
            UiBinder<Widget, MediaCenterTopPanel> {
    }

    @UiField
    ImageMobileButton btnDefault;
    @UiField
    ImageMobileButton btnSpecializedActivity;
    @UiField
    ImageMobileButton btnKeyboard;
    @UiField
    ImageMobileButton btnQuit;
    @UiField
    ImageMobileButton btnStart;
    @UiField
    ImageMobileButton btnUser;
    @UiField
    ImageMobileButton btnShowContextMenu;
    @UiField
    ImageMobileButton btnBack;
    private List<IsCommandableClickHandler> mButtons = null;

    public MediaCenterTopPanel() {
        initWidget(uiBinder.createAndBindUi(this));
        mButtons = new ArrayList<IsCommandableClickHandler>();
        mButtons.addAll(Arrays.asList(new IsCommandableClickHandler[] {
                btnQuit, btnStart, btnBack, btnShowContextMenu }));
    }

    public ImageMobileButton getBtnDefault() {
        return btnDefault;
    }

    public ImageMobileButton getBtnSpecializedActivity() {
        return btnSpecializedActivity;
    }

    public ImageMobileButton getBtnKeyboard() {
        return btnKeyboard;
    }

    public ImageMobileButton getBtnUser() {
        return btnUser;
    }

    @Override
    public List<IsCommandableClickHandler> getClickElements() {
        return mButtons;
    }

}

package com.scurab.web.remotecontrol.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.controls.ImageMobileButton;
import com.scurab.web.remotecontrol.client.controls.MobileButton;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class TVView extends AbstractView {

    private static TVViewUiBinder uiBinder = GWT.create(TVViewUiBinder.class);
    @UiField
    TVTopPanel topPanel;
    @UiField
    MobileButton btn1;
    @UiField
    MobileButton btn2;
    @UiField
    MobileButton btn3;
    @UiField
    MobileButton btn4;
    @UiField
    MobileButton btn5;
    @UiField
    MobileButton btn6;
    @UiField
    MobileButton btn7;
    @UiField
    MobileButton btn8;
    @UiField
    MobileButton btn9;
    @UiField
    MobileButton btnAudio;
    @UiField
    MobileButton btn0;
    @UiField
    MobileButton btnLoop;
    @UiField
    ImageMobileButton btnSavePicture;
    @UiField
    ImageMobileButton btnStartRecording;
    @UiField
    ImageMobileButton btnStopRecording;
    @UiField
    ImageMobileButton btnPlayPause;
    @UiField
    VerticalPanel numericPanel;
    @UiField
    VerticalPanel recordingPanel;
    @UiField
    VerticalPanel userContainer;
    @UiField
    JoyPad joyPad;
    @UiField
    ImageMobileButton btnPlayPrevious;
    @UiField
    ImageMobileButton btnPlayNext;
    @UiField
    ImageMobileButton btnStop;

    private List<IsCommandableClickHandler> mButtons = null;

    interface TVViewUiBinder extends UiBinder<Widget, TVView> {
    }

    public TVView() {
        initWidget(uiBinder.createAndBindUi(this));
        mButtons = new ArrayList<IsCommandableClickHandler>();
        mButtons.addAll(topPanel.getClickElements());
        mButtons.addAll(Arrays.asList(new IsCommandableClickHandler[] { btn1,
                btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnAudio,
                btnLoop, btnSavePicture, btnStopRecording, btnStartRecording,
                btnPlayPrevious, btnPlayNext, btnPlayPause, btnStop }));
        mButtons.addAll(joyPad.getClickElements());
    }

    @Override
    public List<IsCommandableClickHandler> getClickElements() {
        return mButtons;
    }

    public ImageMobileButton getBtnDefault() {
        return topPanel.btnDefault;
    }

    public ImageMobileButton getBtnNumeric() {
        return topPanel.btnNumeric;
    }

    public ImageMobileButton getBtnRecord() {
        return topPanel.btnRecord;
    }

    public ImageMobileButton getBtnUser() {
        return topPanel.btnUser;
    }

    public TVTopPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(TVTopPanel topPanel) {
        this.topPanel = topPanel;
    }

    public JoyPad getJoyPad() {
        return joyPad;
    }

    public void setJoyPad(JoyPad joyPad) {
        this.joyPad = joyPad;
    }

    public VerticalPanel getNumericPanel() {
        return numericPanel;
    }

    public void setNumericPanel(VerticalPanel numericPanel) {
        this.numericPanel = numericPanel;
    }

    public VerticalPanel getRecordingPanel() {
        return recordingPanel;
    }

    public void setRecordingPanel(VerticalPanel recordingPanel) {
        this.recordingPanel = recordingPanel;
    }

    public VerticalPanel getUserContainer() {
        return userContainer;
    }

}

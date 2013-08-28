package com.scurab.web.remotecontrol.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.controls.MobileButton;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class VideoPlayerView extends AbstractView {

    private static VideoPlayerViewUiBinder uiBinder = GWT
            .create(VideoPlayerViewUiBinder.class);
    @UiField
    PlayerPad playerPad;
    @UiField
    JoyPad joyPad;
    @UiField
    VerticalPanel videoPanel;
    @UiField
    VerticalPanel audioPanel;
    @UiField
    VerticalPanel dvdPanel;
    @UiField
    VerticalPanel subtitlesPanel;
    @UiField
    VerticalPanel userPanel;
    @UiField
    VideoPlayerTopPanel topPanel;

    @UiField
    MobileButton btnFullScreen;
    @UiField
    MobileButton btnRatio;
    @UiField
    MobileButton btnSaveScreen;
    @UiField
    MobileButton btnWindowBigger;
    @UiField
    MobileButton btnWindowSmaller;
    @UiField
    MobileButton btnVideoSpeedDown;
    @UiField
    MobileButton btnVideoSpeedReset;
    @UiField
    MobileButton btnVideoSpeedUp;
    @UiField
    MobileButton btnAudioDelayUp;
    @UiField
    MobileButton btnAudioDelayDown;
    @UiField
    MobileButton btnTrack;
    @UiField
    MobileButton btnOutput;
    @UiField
    MobileButton btnSubDelayUp;
    @UiField
    MobileButton btnSubDelayDown;
    @UiField
    MobileButton btnSubUp;
    @UiField
    MobileButton btnSubChange;
    @UiField
    MobileButton btnSubDown;
    @UiField
    MobileButton btnShowDVDMenu;

    private List<IsCommandableClickHandler> mButtons = null;

    interface VideoPlayerViewUiBinder extends UiBinder<Widget, VideoPlayerView> {
    }

    public VideoPlayerView() {
        initWidget(uiBinder.createAndBindUi(this));

        mButtons = new ArrayList<IsCommandableClickHandler>();
        mButtons.addAll(topPanel.getClickElements());
        mButtons.addAll(Arrays.asList(new IsCommandableClickHandler[] {
                btnFullScreen, btnRatio, btnSaveScreen, btnWindowBigger,
                btnWindowSmaller, btnVideoSpeedDown, btnVideoSpeedReset,
                btnVideoSpeedUp, btnAudioDelayUp, btnAudioDelayDown, btnTrack,
                btnOutput, btnSubDelayUp, btnSubDelayDown, btnSubUp,
                btnSubChange, btnSubDown, btnShowDVDMenu }));
        mButtons.addAll(playerPad.getClickElements());
        mButtons.addAll(joyPad.getClickElements());
    }

    public PlayerPad getPlayerPad() {
        return playerPad;
    }

    public VerticalPanel getVideoPanel() {
        return videoPanel;
    }

    public MobileButton getBtnFullScreen() {
        return btnFullScreen;
    }

    public MobileButton getBtnRatio() {
        return btnRatio;
    }

    public MobileButton getBtnSaveScreen() {
        return btnSaveScreen;
    }

    public VerticalPanel getAudioPanel() {
        return audioPanel;
    }

    public VerticalPanel getDvdPanel() {
        return dvdPanel;
    }

    public VerticalPanel getSubtitlesPanel() {
        return subtitlesPanel;
    }

    public JoyPad getJoyPad() {
        return joyPad;
    }

    public VerticalPanel getUserPanel() {
        return userPanel;
    }

    public VideoPlayerTopPanel getTopPanel() {
        return topPanel;
    }

    @Override
    public List<IsCommandableClickHandler> getClickElements() {
        return mButtons;
    }
}

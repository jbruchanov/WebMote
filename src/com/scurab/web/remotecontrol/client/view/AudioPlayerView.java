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

public class AudioPlayerView extends AbstractView {

    private static AudioPlayerViewUiBinder uiBinder = GWT
            .create(AudioPlayerViewUiBinder.class);
    @UiField
    AudioPlayerTopPanel topPanel;
    @UiField
    PlayerPad playerPad;
    @UiField
    VerticalPanel layoutPanel;
    @UiField
    VerticalPanel userPanel;
    @UiField
    MobileButton btnRepeat;
    @UiField
    MobileButton btnShuffle;
    @UiField
    MobileButton btnCollapse;
    @UiField
    MobileButton btnHide;

    interface AudioPlayerViewUiBinder extends UiBinder<Widget, AudioPlayerView> {
    }

    private List<IsCommandableClickHandler> mButtons = null;

    public AudioPlayerView() {
        initWidget(uiBinder.createAndBindUi(this));

        mButtons = new ArrayList<IsCommandableClickHandler>();
        mButtons.addAll(getTopPanel().getClickElements());
        mButtons.addAll(Arrays.asList(new IsCommandableClickHandler[] {
                btnRepeat, btnShuffle, btnCollapse, btnHide }));
        mButtons.addAll(playerPad.getClickElements());
    }

    @Override
    public List<IsCommandableClickHandler> getClickElements() {
        return mButtons;
    }

    public AudioPlayerTopPanel getTopPanel() {
        return topPanel;
    }

    public PlayerPad getPlayerPad() {
        return playerPad;
    }

    public void setPlayerPad(PlayerPad playerPad) {
        this.playerPad = playerPad;
    }

    public VerticalPanel getLayoutPanel() {
        return layoutPanel;
    }

    public void setLayoutPanel(VerticalPanel layoutPanel) {
        this.layoutPanel = layoutPanel;
    }

    public VerticalPanel getUserPanel() {
        return userPanel;
    }

    public void setUserPanel(VerticalPanel userPanel) {
        this.userPanel = userPanel;
    }
}

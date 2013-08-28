package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.event.ChangePresenterEvent;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.AudioPlayerView;
import com.scurab.web.remotecontrol.client.view.ConfigView;
import com.scurab.web.remotecontrol.client.view.DiskBrowserView;
import com.scurab.web.remotecontrol.client.view.MainView;
import com.scurab.web.remotecontrol.client.view.MainView2;
import com.scurab.web.remotecontrol.client.view.PicturesView;
import com.scurab.web.remotecontrol.client.view.TVView;
import com.scurab.web.remotecontrol.client.view.VideoPlayerView;
import com.scurab.web.remotecontrol.client.view.VolumeControl;

public class MainViewPresenter extends BasePresenter {
    public MainView mDisplay;

    private enum ButtonType {
        TV, Video, Audio, Volume, Pictures, Next, FileBrowser, Config
    }

    public MainViewPresenter(DataService dataService, HandlerManager eventBus,
            MainView display) {
        super(dataService, eventBus, display);
        mDisplay = display;
        bind();
    }

    private void bind() {
        mDisplay.getTVButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onButtonClick(ButtonType.TV);
            }
        });
        mDisplay.getVideoButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onButtonClick(ButtonType.Video);
            }
        });
        mDisplay.getAudioButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onButtonClick(ButtonType.Audio);
            }
        });
        mDisplay.getPicturesButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onButtonClick(ButtonType.Pictures);
            }
        });
        mDisplay.getVolumeButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onButtonClick(ButtonType.Volume);
            }
        });
        mDisplay.getConfigButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onButtonClick(ButtonType.Config);
            }
        });
        mDisplay.getNextButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onButtonClick(ButtonType.Next);
            }
        });
        mDisplay.getFileBrowserButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onButtonClick(ButtonType.FileBrowser);
            }
        });
    }

    public void onButtonClick(ButtonType type) {
        try {
            BasePresenter pres = null;
            switch (type) {
                case Audio:
                    checkApp(RemoteControl.AudioPlayer);
                    pres = new AudioPlayerPresenter(mDataService, mEventBus,
                            new AudioPlayerView());
                    break;
                case FileBrowser:
                    pres = new DiskBrowserPresenter(mDataService, mEventBus,
                            new DiskBrowserView());
                    break;
                case Next:
                    pres = new MainView2Presenter(mDataService, mEventBus,
                            new MainView2());
                    break;
                case Pictures:
                    checkApp(RemoteControl.PicturesViewer);
                    pres = new PicturesPresenter(mDataService, mEventBus,
                            new PicturesView());
                    break;
                case TV:
                    checkApp(RemoteControl.TVAppliation);
                    pres = new TVPresenter(mDataService, mEventBus,
                            new TVView());
                    break;
                case Video:
                    checkApp(RemoteControl.VideoPlayer);
                    pres = new VideoPlayerPresenter(mDataService, mEventBus,
                            new VideoPlayerView());
                    break;
                case Volume:
                    pres = new VolumeControlPresenter(mDataService, mEventBus,
                            new VolumeControl());
                    break;
                case Config:
                    pres = new ConfigPresenter(mDataService, mEventBus,
                            new ConfigView());
                    break;
                default:
                    break;
            }
            onChangePresenter(pres);
        } catch (Exception e) {
            Window.alert(e.getMessage());
        }
    }

    public void onChangePresenter(BasePresenter presenter) {
        mEventBus.fireEvent(new ChangePresenterEvent(presenter));
    }

    @Override
    public String getName() {
        return "MainView";
    }
}

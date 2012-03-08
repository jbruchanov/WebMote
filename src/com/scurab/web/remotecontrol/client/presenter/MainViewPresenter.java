package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.event.ChangePresenterEvent;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.AudioPlayerView;
import com.scurab.web.remotecontrol.client.view.DiskBrowserView;
import com.scurab.web.remotecontrol.client.view.MainView;
import com.scurab.web.remotecontrol.client.view.PicturesView;
import com.scurab.web.remotecontrol.client.view.TVView;
import com.scurab.web.remotecontrol.client.view.VideoPlayerView;
import com.scurab.web.remotecontrol.client.view.VolumeControl;

public class MainViewPresenter extends BasePresenter
{
	public MainView mDisplay;
	private enum ButtonType
	{
		TV,Video, Audio, Volume, Pictures, MediaCenter, Next, FileBrowser
	}
	
	public MainViewPresenter(DataService dataService, HandlerManager eventBus, MainView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
		bind();
	}
	
	private void bind()
	{
		mDisplay.getTVButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.TV);}});
		mDisplay.getVideoButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.Video);}});
		mDisplay.getAudioButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.Audio);}});
		mDisplay.getPicturesButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.Pictures);}});
		mDisplay.getVolumeButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.Volume);}});
		mDisplay.getMediaCenterButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.MediaCenter);}});
		mDisplay.getNextButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.Next);}});
		mDisplay.getFileBrowserButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.FileBrowser);}});
	}
	
	public void onButtonClick(ButtonType type)
	{
		BasePresenter pres = null;
		switch(type)
		{
			case Audio:
				pres = new AudioPlayerPresenter(mDataService, mEventBus, new AudioPlayerView());
				break;
			case FileBrowser:
				pres= new DiskBrowserPresenter(mDataService,mEventBus,new DiskBrowserView());
				break;
			case MediaCenter:break;
			case Next:break;
			case Pictures:
				pres = new PicturesPresenter(mDataService, mEventBus, new PicturesView());
				break;
			case TV:
				pres= new TVPresenter(mDataService,mEventBus,new TVView());
				break;
			case Video:
				pres = new VideoPlayerPresenter(mDataService, mEventBus, new VideoPlayerView());
				break;
			case Volume:
				pres = new VolumeControlPresenter(mDataService, mEventBus, new VolumeControl());
				break;
			default:
				break;
		}
		
		onChangePresenter(pres);
	}
	
	public void onChangePresenter(BasePresenter presenter)
	{
		mEventBus.fireEvent(new ChangePresenterEvent(presenter));
//		RootPanel.get().clear();
//		RootPanel.get().add(presenter.asWidget());
	}

	@Override
	public String getName()
	{
		return "MainView";
	}
}

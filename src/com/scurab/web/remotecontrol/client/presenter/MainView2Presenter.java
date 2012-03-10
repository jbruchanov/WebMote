package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.scurab.web.remotecontrol.client.event.ChangePresenterEvent;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.AudioPlayerView;
import com.scurab.web.remotecontrol.client.view.DiskBrowserView;
import com.scurab.web.remotecontrol.client.view.MainView;
import com.scurab.web.remotecontrol.client.view.MainView2;
import com.scurab.web.remotecontrol.client.view.PicturesView;
import com.scurab.web.remotecontrol.client.view.ShutdownView;
import com.scurab.web.remotecontrol.client.view.TVView;
import com.scurab.web.remotecontrol.client.view.VideoPlayerView;
import com.scurab.web.remotecontrol.client.view.VolumeControl;

public class MainView2Presenter extends BasePresenter
{
	public MainView2 mDisplay;
	private enum ButtonType
	{
		ShutDown, TaskManager, RemoteDesktop, Keyboard, WakeOnLan, Display, JoyPad, IRDevices
	}
	
	public MainView2Presenter(DataService dataService, HandlerManager eventBus, MainView2 display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
		bind();
	}
	
	private void bind()
	{
		mDisplay.getShutdownButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.ShutDown);}});
		mDisplay.getTaskManagerButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.TaskManager);}});
		mDisplay.getRemoteDesktopButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.RemoteDesktop);}});
		mDisplay.getKeyboardButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.Keyboard);}});
		mDisplay.getWakeOnLanButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.WakeOnLan);}});
		mDisplay.getDisplayButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.Display);}});
		mDisplay.getJoyPadButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.JoyPad);}});
		mDisplay.getIRDevicesButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.IRDevices);}});
	}
	
	public void onButtonClick(ButtonType type)
	{
		BasePresenter pres = null;
		switch(type)
		{
//			case Audio:
//				pres = new AudioPlayerPresenter(mDataService, mEventBus, new AudioPlayerView());
//				break;
		case ShutDown:
			pres = new ShutdownPresenter(mDataService, mEventBus, new ShutdownView());
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
		return "MainView2";
	}
}

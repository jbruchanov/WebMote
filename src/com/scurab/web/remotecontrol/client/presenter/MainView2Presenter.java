package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.SystemCommand;
import com.scurab.web.remotecontrol.client.components.MonitorDialog;
import com.scurab.web.remotecontrol.client.components.MonitorDialog.ContextType;
import com.scurab.web.remotecontrol.client.event.ChangePresenterEvent;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.DesktopView;
import com.scurab.web.remotecontrol.client.view.IRDeviceView;
import com.scurab.web.remotecontrol.client.view.JoyPadView;
import com.scurab.web.remotecontrol.client.view.KeyboardView;
import com.scurab.web.remotecontrol.client.view.MainView2;
import com.scurab.web.remotecontrol.client.view.ShutdownView;
import com.scurab.web.remotecontrol.client.view.TaskManagerView;

public class MainView2Presenter extends BaseControlPresenter
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
//		mDisplay.getWakeOnLanButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.WakeOnLan);}});
		mDisplay.getDisplayButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.Display);}});
		mDisplay.getJoyPadButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.JoyPad);}});
		mDisplay.getIRDevicesButton().addClickHandler(new ClickHandler(){@Override public void onClick(ClickEvent event){onButtonClick(ButtonType.IRDevices);}});
	}
	
	public void onButtonClick(ButtonType type)
	{
		BasePresenter pres = null;
		switch(type)
		{
		case ShutDown:
			pres = new ShutdownPresenter(mDataService, mEventBus, new ShutdownView());
			break;
		case TaskManager:
			pres = new TaskManagerPresenter(mDataService, mEventBus, new TaskManagerView());			
				break;
		case Display:
			onDisplayClick();
			break;
		case JoyPad:
			pres= new JoyPadViewPresenter(mDataService, mEventBus, new JoyPadView());
			break;
		case Keyboard:
			pres = new KeyboardPresenter(mDataService, mEventBus, new KeyboardView());
			break;
		case RemoteDesktop:
			pres = new DesktopViewPresenter(mDataService, mEventBus, new DesktopView());
			break;
		case IRDevices:
			pres = new IRDevicePresenter(mDataService, mEventBus, new IRDeviceView());
			break;
		default:
		}
		
		onChangePresenter(pres);
	}
	
	private void onDisplayClick()
	{
		MonitorDialog.showDialog(new MonitorDialog.OnClickListener()
		{
			@Override
			public void onClick(ContextType contextCommand)
			{
				SystemCommand sc = new SystemCommand();
				if(contextCommand == ContextType.On)
					sc.turnMonitorOn();
				else
					sc.turnMonitorOff();
				onSendCommand(sc);
			}
		});
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

	@Override
	protected Command getCommand(String command)
	{
		return null;
	}
}

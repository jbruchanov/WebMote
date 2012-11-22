package com.scurab.web.remotecontrol.client.presenter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Header;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.RemoteControl.PropertyKeys;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.InfoCommand;
import com.scurab.web.remotecontrol.client.event.ChangePresenterEvent;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.tools.JsonSimpleParser;
import com.scurab.web.remotecontrol.client.view.ConfigView;
import com.scurab.web.remotecontrol.client.view.FavoritiesView;

public class ConfigPresenter extends BaseControlPresenter
{
	ConfigView mDisplay = null;
	
	public ConfigPresenter(DataService dataService, HandlerManager eventBus, ConfigView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
		mDataService = new MockDataService();
		bind();
		new Timer() {@Override public void run(){load();}}.schedule(50); 
	}
	
	protected void load()
	{
		onSendCommand(new InfoCommand(), new RequestCallback()
		{
			@Override
			public void onResponseReceived(Request request, Response response)
			{
				try
				{
					InfoCommand ic = JsonSimpleParser.parseInfoCommand(response.getText());
					HashMap<String, List<String>> data = ic.getApplications();
					onLoadedData(data);
				}
				catch(Exception e)
				{
					Window.alert(e.getMessage());
				}
			}
			
			@Override
			public void onError(Request request, Throwable exception)
			{
				Window.alert(exception.getMessage());
			}
		});
	}
	
	protected void onLoadedData(HashMap<String, List<String>> data) throws Exception
	{
		for(String key : data.keySet())
		{
			initValues(key,data.get(key));
		}

		if(!data.containsKey(RemoteControl.PropertyKeys.IRDEVICE)) //fix for no WinLIRC
			getListBox(RemoteControl.PropertyKeys.IRDEVICE).addItem("", "");
	}
	
	private void initValues(String key, List<String> items) throws Exception
	{
		
		ListBox cmb = getListBox(key);
		
		if(cmb != null)
		{
			Collections.sort(items);
			//cmb.setSelectedIndex(-1);
			cmb.addItem("", "");
			for(int i = 0;i<items.size();i++)
			{
				String item = items.get(i);
				cmb.addItem(item, item);
				if(RemoteControl.getProperty(key,"").equals(item))
					cmb.setSelectedIndex(i+1);//because first is not selected
			}
		}
		else
		{
			throw new Exception("Wrong key:" + key);
		}
	}
	
	private ListBox getListBox(String key)
	{
		if(RemoteControl.PropertyKeys.TVAPPLIATION.equals(key))
			return mDisplay.getCmbTV();
		else if(RemoteControl.PropertyKeys.VIDEOPLAYER.equals(key))
			return mDisplay.getCmbVideoPlayer();
		else if(RemoteControl.PropertyKeys.AUDIOPLAYER.equals(key))
			return mDisplay.getCmbAudioPlayer();
		else if(RemoteControl.PropertyKeys.PICTURESVIEWER.equals(key))
			return mDisplay.getCmbPictureViewer();
		else if(RemoteControl.PropertyKeys.MEDIACENTER.equals(key))
			return mDisplay.getCmbMediaCenter();
		else if(RemoteControl.PropertyKeys.IRDEVICE.equals(key))
			return mDisplay.getCmbIRDevices();
		else
			return null;
	}
	
	private void bind()
	{
		mDisplay.getTxtPIN().setText(RemoteControl.getPIN());
		mDisplay.getBtnSave().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{			
				onSave();
			}
		});
		mDisplay.getBtnFavorites().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				mEventBus.fireEvent(new ChangePresenterEvent(new FavoritiesPresenter(mDataService, mEventBus, new FavoritiesView())));
			}
		});
		
		mDisplay.getBtnFavorites().setEnabled(Storage.isLocalStorageSupported());
		
	}
	
	public void onSave()
	{
		try
		{
			String pin = mDisplay.getTxtPIN().getValue();
			if(pin != null && pin.trim().length() > 0)
				RemoteControl.setProperty(PropertyKeys.PIN, pin);
			else
			{
				throw new Exception(RemoteControl.Words.InvalidPIN());
			}
			
			String[] keys = RemoteControl.PropertyKeys.ALL_APP_PROPERTY_KEYS;
			for(String key : keys)
			{
				try
				{
					ListBox lb = getListBox(key);
					String value = lb.getValue(lb.getSelectedIndex());
					if(value != null && value.length() > 0)
						RemoteControl.setProperty(key, value);
				}
				catch(Exception e)
				{
					//ignore trouble about reading
				}
			}
			Window.alert(RemoteControl.Words.OK());
		}
		catch(Exception e)
		{
			Window.alert(e.getMessage());
		}
	}
	
	@Override
	public String getName()
	{
		return "ConfigPresenter";
	}

	@Override
	protected Command getCommand(String command)
	{
		return null;
	}
	
	private class MockDataService extends DataService
	{
		public MockDataService()
		{			
		}
		
		@Override
		public void sendCommand(Command c, RequestCallback rc) throws RequestException
		{
			Response r = new Response()
			{
				@Override
				public String getText()
				{
					//return "{\"Audio\":[\"WinAmp\"],\"MediaCenter\":[],\"Picture\":[\"Media Center Pictures\",\"Windows Photo Viewer\"],\"Television\":[\"Avermedia TV\"],\"Video\":[\"Media Player classic\",\"VLC Player\"],\"WinLIRC\":[\"logitech_z680\",\"Panasonic_EUR644340\"]}";
//					return "{\"Audio\":[\"WinAmp\"],\"MediaCenter\":[],\"Picture\":[\"Media Center Pictures\",\"Windows Photo Viewer\"],\"Television\":[\"Avermedia TV\"],\"Video\":[\"Media Player classic\",\"VLC Player\"]}";
					return "{\"ComputerName\":\"ELEPHANT-W8\",\"Applications\":{\"Picture\":[\"Windows Photo Viewer\",\"Media Portal Pictures\",\"Media Center Pictures\",\"XBMC Pictures\",\"IrfanView\",\"Picasa\"],\"Video\":[\"VLC PLayer\",\"Media Center Video\",\"Media Portal Video\",\"Media Player classic\",\"Windows Media Player Video\",\"XBMC Video\"],\"Television\":[\"Media Portal TV\",\"Media Center TV\",\"Avermedia TV\"],\"Audio\":[\"Media Center Audio\",\"WinAmp\",\"XBMC Audio\",\"Media Portal Audio\",\"Windows Media Player Audio\"],\"MediaCenter\":[\"Media Center\",\"XBMC\",\"Media Portal\"]},\"MACs\":[\"DC:A9:71:1A:0A:C2\",\"DC:A9:71:1A:0A:C3\",\"00:15:5D:C1:F8:F9\",\"08:00:27:00:F0:B2\"],\"Command\":\"InfoCommand\",\"ProtocolVersion\":0}";
				}
				
				@Override
				public String getStatusText()
				{
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public int getStatusCode()
				{
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public String getHeadersAsString()
				{
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Header[] getHeaders()
				{
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getHeader(String header)
				{
					// TODO Auto-generated method stub
					return null;
				}
			};
			rc.onResponseReceived(null, r);
		}
	}
}

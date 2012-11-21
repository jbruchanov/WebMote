package com.scurab.web.remotecontrol.client.presenter;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Header;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.scurab.web.remotecontrol.client.R;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.commands.AudioPlayerCommand;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.FileManagerCommand;
import com.scurab.web.remotecontrol.client.commands.MCICommand;
import com.scurab.web.remotecontrol.client.commands.PhotoViewerCommand;
import com.scurab.web.remotecontrol.client.commands.ProcessCommand;
import com.scurab.web.remotecontrol.client.commands.VideoPlayerCommand;
import com.scurab.web.remotecontrol.client.components.FileBrowserContextMenu;
import com.scurab.web.remotecontrol.client.components.FileBrowserContextMenu.ContextType;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.tools.JsonSimpleParser;
import com.scurab.web.remotecontrol.client.tools.StringUtils;
import com.scurab.web.remotecontrol.client.view.DiskBrowserItem;
import com.scurab.web.remotecontrol.client.view.DiskBrowserView;
import com.scurab.web.remotecontrol.client.view.DiskBrowserItem.Type;

public class DiskBrowserPresenter extends BaseControlPresenter
{
	private DiskBrowserView mDisplay = null;
	private List<HashMap<String,String>> mCurrentData = null;
	private String mCurrentDir = "";
	
	private final static int TYPE_FILE = 20;
	
	public DiskBrowserPresenter(DataService dataService, HandlerManager eventBus, DiskBrowserView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
//		mDataService = new MockDataService();
		loadData(null);
		bind();
	}
	
	private void bind()
	{
		mDisplay.getBtnCustomLocation().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onCustomLocationClick();
			}
		});
		
		mDisplay.getTglFilter().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				loadData(mCurrentDir);
			}
		});
		
		
	}
	
	private String getFilter()
	{
		boolean enabled = mDisplay.getTglFilter().getValue();
		String filter = mDisplay.getTxtFilter().getText();
		if(!enabled || filter.trim().length() == 0)
			return null;
		else
			return filter;
	}
	
	protected void onCustomLocationClick()
	{
		String custom = Window.prompt("Custom location", "");
		loadData(custom);
	}
	
	private void loadData(final String location)
	{
		FileManagerCommand fmc = new FileManagerCommand();
		if(location != null)
			fmc.Root = location;
		fmc.Filter = getFilter();
		
		try
		{
			mDisplay.setProgressVisible(true);
			mDataService.sendCommand(fmc, new RequestCallback()
			{
				@Override
				public void onResponseReceived(Request request, Response response)
				{					
					mCurrentDir = location;
					String s = response.getText();
					mDisplay.getLblCurrentLocation().setText(mCurrentDir);
					mDisplay.getContentPanel().clear();
					mCurrentData = JsonSimpleParser.parse(s);
					onLoadData(mCurrentData);
				}
				
				@Override
				public void onError(Request request, Throwable exception)
				{
					mDisplay.setProgressVisible(false);
					Window.alert("ERROR " + exception.getMessage());
				}
			});
		}
		catch(Exception e)
		{
			Window.alert(e.getMessage());
		}
	}
	
	
	
	protected void onLoadData(List<HashMap<String,String>> data)
	{
		for(HashMap<String,String> row : data)
		{
			String n = row.get("N");
			if(n.endsWith("\\\\"))
				n = n.substring(0,n.length()-1); //shit about double \\ on disks
			final String dir = n;
			String t = row.get("T");
			final int type = Integer.parseInt(t);
			final DiskBrowserItem b = new DiskBrowserItem(t,dir);
			b.setWidth("100%");
			b.setOnContextButtonClickListener(new DiskBrowserItem.OnContextButtonClickListener()
			{
				@Override
				public void onClick(Type t, String value)
				{
					if(t == Type.Return)
						onItemClick(dir,type);					
					else
					{
						onContextItemClick(b);
					}
				}
			});
			b.addClickHandler(new ClickHandler()
			{
				@Override
				public void onClick(ClickEvent event)
				{
					onItemClick(dir,type);
				}
			});
			mDisplay.getContentPanel().add(b);
		}
		mDisplay.setProgressVisible(false);
	}
	
	protected void onContextItemClick(DiskBrowserItem item)
	{
		FileBrowserContextMenu.showDialog(item, new FileBrowserContextMenu.OnClickListener()
		{
			@Override
			public void onClick(DiskBrowserItem item, FileBrowserContextMenu.ContextType command)
			{
				String loc = item.getValue();
				if(item.getType().getCode() >= 10)
				{
					String cd = (mCurrentDir == null ||  mCurrentDir.trim().length() == 0) ? "" : mCurrentDir;
					if(cd.trim().length() > 0 && !cd.endsWith("\\")) cd += "\\";
					loc = "\"" + cd + item.getValue() + "\"";
				}
				
				if(command == ContextType.AddToFavorites)
					showAddToFavsMenu(item, loc);
				else
					onContextSendCommand(loc,command);
			}
		});
	}
	
	private void showAddToFavsMenu(DiskBrowserItem item, final String locality)
	{
		FileBrowserContextMenu.showDialog(item, new FileBrowserContextMenu.OnClickListener()
		{
			@Override
			public void onClick(DiskBrowserItem item, FileBrowserContextMenu.ContextType command)
			{
				try
				{
					String category = "";
					if(command == ContextType.VideoPlayer) category = RemoteControl.PropertyKeys.VIDEOPLAYER;
					else if(command == ContextType.AudioPlayer) category = RemoteControl.PropertyKeys.AUDIOPLAYER;
					else if(command == ContextType.PicturesViewer) category = RemoteControl.PropertyKeys.PICTURESVIEWER;
					String name = item.getValue().replace(R.Constants.VALUE_SEPARATOR, "");
					RemoteControl.addToFavorites(category, name, locality);
				}
				catch(Exception e)
				{
					Window.alert(e.getMessage());
				}
			}
		},true);
	}
	
	protected void onContextSendCommand(String location, FileBrowserContextMenu.ContextType what)
	{
		Command c = getCommand(location, what);
		try
		{
			mDisplay.setProgressVisible(true);
			mDataService.sendCommand(c, new RequestCallback()
			{
				@Override
				public void onResponseReceived(Request request, Response response)
				{
					mDisplay.setProgressVisible(false);
					//done
				}
				
				@Override
				public void onError(Request request, Throwable exception)
				{
					mDisplay.setProgressVisible(false);
					Window.alert("ERROR " + exception.getMessage());
				}
			});
		}
		catch(Exception e)
		{
			Window.alert(e.getMessage());
		}
	}
	
	private Command getCommand(String location, FileBrowserContextMenu.ContextType what)
	{
		Command c = null;
		switch(what)
		{
			case AudioPlayer:
				AudioPlayerCommand apc = new AudioPlayerCommand(RemoteControl.AudioPlayer);
				apc.setMethod("Start");
				apc.MethodParameter = location;
				c = apc;
				break;
			case VideoPlayer:
				VideoPlayerCommand vpc = new VideoPlayerCommand(RemoteControl.VideoPlayer);
				vpc.setMethod("Start");
				vpc.MethodParameter = location;
				c = vpc;
				break;
			case PicturesViewer:
				PhotoViewerCommand ppc = new PhotoViewerCommand(RemoteControl.PicturesViewer);
				ppc.setMethod("Start");
				ppc.MethodParameter = location;
				c = ppc;
				break;
			case StartByOS:
				ProcessCommand pc = new ProcessCommand();
				pc.Run(location, null);
				c = pc;
				break;
			case Open:
				MCICommand mcic = new MCICommand();
				mcic.openCD(location);
				c = mcic;
				break;
			case Close:
				MCICommand mcic2 = new MCICommand();
				mcic2.closeCD(location);
				c = mcic2;
				break;
		}
		return c;
	}
	
	protected void onItemClick(String dir, int type)
	{
		String file = ""; 
		if(type == -1)
		{
			file = StringUtils.getParentDir(mCurrentDir);
			loadData(file);
			
		}
		else if(type != TYPE_FILE)
		{
			if(type == 10)
			{
				if(mCurrentDir != null && !mCurrentDir.endsWith("\\"))				
					file = mCurrentDir + "\\" + dir;
				else
					file = mCurrentDir + dir;
			}
			else
				file = dir;
			loadData(file);
		}
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
		private int a = 0;
		@Override
		public void sendCommand(Command c, RequestCallback rc) throws RequestException
		{
			Response r = new Response()
			{
				String diska = "[{\"N\":\"C:\\\",\"T\":1},{\"N\":\"D:\\\",\"T\":1},{\"N\":\"F:\\\",\"T\":1},{\"N\":\"G:\\\",\"T\":3},{\"N\":\"H:\\\",\"T\":3},{\"N\":\"L:\\\",\"T\":1},{\"N\":\"R:\\\",\"T\":2},{\"N\":\"W:\\\",\"T\":2},{\"N\":\"X:\\\",\"T\":2}]";				
				String diskc = "[{\"N\":\"..\",\"T\":-1},{\"N\":\"$RECYCLE.BIN\",\"T\":10},{\"N\":\"-= Photos =-\",\"T\":10},{\"N\":\"0Work\",\"T\":10},{\"N\":\"1Skola\",\"T\":10},{\"N\":\"Release\",\"T\":10},{\"N\":\"System Volume Information\",\"T\":10},{\"N\":\"tmp\",\"T\":10},{\"N\":\"Utils\",\"T\":10},{\"N\":\"VMS\",\"T\":10},{\"N\":\"CV-Resume.pdf\",\"T\":20},{\"N\":\"EnglishDictionary.xls\",\"T\":20},{\"N\":\"LEDs_Hack_1.2.10.apk\",\"T\":20},{\"N\":\"Nolf Disc 2.bin\",\"T\":20},{\"N\":\"Nolf Disc 2.cue\",\"T\":20},{\"N\":\"NOLF_CD1.iso\",\"T\":20},{\"N\":\"PlatbyDatumy.xls\",\"T\":20},{\"N\":\"Visual Studio 2008.zip\",\"T\":20}]";				
				String[] data = new String[] {diska,diskc,diskc,diskc,diskc,diskc,diskc};
				@Override
				public String getText()
				{
					return data[a++ % data.length];
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

	@Override
	public String getName()
	{
		return "DiskBrowser";
	}
}

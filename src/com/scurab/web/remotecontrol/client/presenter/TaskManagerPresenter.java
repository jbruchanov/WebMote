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
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.ProcessCommand;
import com.scurab.web.remotecontrol.client.components.TaskManagerContextMenu;
import com.scurab.web.remotecontrol.client.components.TaskManagerContextMenu.ContextType;
import com.scurab.web.remotecontrol.client.datamodel.Proc;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.tools.JsonSimpleParser;
import com.scurab.web.remotecontrol.client.view.ProcessItem;
import com.scurab.web.remotecontrol.client.view.TaskManagerView;

public class TaskManagerPresenter extends BaseControlPresenter
{
	private TaskManagerView mDisplay = null;
	
	public TaskManagerPresenter(DataService dataService, HandlerManager eventBus, TaskManagerView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
//		mDataService = new MockDataService();
		bind();
		onReload();
	}
	
	private void bind()
	{
		mDisplay.getBtnReload().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onReload();
			}
		});
		
		mDisplay.getBtnStartCustomProcess().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onStartCustomProcess();
			}
		});
	}
	
	public void onReload()
	{
		ProcessCommand  pc = new ProcessCommand();
		pc.Get();
		try
		{
			mDisplay.setProgressVisible(true);
			mDataService.sendCommand(pc,new RequestCallback()
			{
				@Override
				public void onResponseReceived(Request request, Response response)
				{
					List<HashMap<String,String>> data = JsonSimpleParser.parse(response.getText());
					onLoadedData(data);
				}
				
				@Override
				public void onError(Request request, Throwable exception)
				{
					mDisplay.setProgressVisible(false);
					Window.alert(exception.getMessage());
				}
			});
		}
		catch(Exception e)
		{
			//ignore it
		}
	}
	
	public void onLoadedData(List<HashMap<String,String>> data)
	{
		mDisplay.getContentPanel().clear();
		for(HashMap<String,String> row : data)
		{
			final Proc p = new Proc();
			p.Name = row.get("N");
			p.ID = Integer.parseInt(row.get("ID"));
			if(row.containsKey("W"))
			{
				String s = row.get("W").trim();
				p.Description = (s.length() > 0 ? s : null);
			}
			
			ProcessItem pi = new ProcessItem(p);
			mDisplay.getContentPanel().add(pi);
			pi.addClickHandler(new ClickHandler()
			{
				@Override
				public void onClick(ClickEvent event)
				{
					onProcessItemClick(p);
				}
			});
		}
		mDisplay.setProgressVisible(false);
	}
	
	public void onProcessItemClick(Proc p)
	{
		TaskManagerContextMenu.showDialog(p, new TaskManagerContextMenu.OnClickListener()
		{
			@Override
			public void onClick(Proc item, ContextType contextCommand)
			{
				onContextItemSelected(item, contextCommand);
			}
		});
	}
	
	public void onContextItemSelected(Proc item, ContextType cmd)
	{
		ProcessCommand pc = new ProcessCommand();
		switch(cmd)
		{
			case Active:
				pc.ActivateProcess(item.ID);
				break;
			case Kill:
				pc.Kill(item.ID);				
				break;
		}
		onSendCommand(pc);
		
		if(cmd == ContextType.Kill)
			onReload();
	}
	
	public void onStartCustomProcess()
	{
		String q = Window.prompt(RemoteControl.Words.CustomStart(), "");
		if(q != null && q.trim().length() > 0)
		{
			String s = q.trim();
			String[] data = s.split(" ");			
			onSendCommand(getStartCustomCommand(data));
			onReload();
		}
	}
	
	private ProcessCommand getStartCustomCommand(String... params)
	{
		ProcessCommand pc = new ProcessCommand();
		pc.Run(params[0], null);
		if(params.length > 1)
		{
			StringBuilder sb = new StringBuilder();
			for(int i = 1;i<params.length;i++)
			{
				sb.append(params[i] + " "); 
			}
			pc.MethodParameter = sb.toString().trim();
		}
		return pc;
	}

	@Override
	protected Command getCommand(String command)
	{
		return null;
	}

	@Override
	public String getName()
	{
		return "ProcessesViewPresenter";
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
					return "[{\"ID\":4792,\"N\":\"adb\",\"W\":\"\",\"D\":null},{\"ID\":1404,\"N\":\"atieclxx\",\"W\":\"\",\"D\":\"\"},{\"ID\":456,\"N\":\"atiesrxx\",\"W\":\"\",\"D\":\"\"},{\"ID\":1140,\"N\":\"audiodg\",\"W\":\"\",\"D\":\"\"},{\"ID\":2832,\"N\":\"AVerHIDReceiver\",\"W\":\"\",\"D\":\"HIDRec Application\"},{\"ID\":2892,\"N\":\"AVerQuick\",\"W\":\"\",\"D\":\"AVerQuick\"},{\"ID\":1768,\"N\":\"AVerRemote\",\"W\":\"\",\"D\":\"\"},{\"ID\":1796,\"N\":\"AVerScheduleService\",\"W\":\"\",\"D\":\"\"},{\"ID\":3824,\"N\":\"AVerTV\",\"W\":\"AVerTV - Video\",\"D\":\"AVerMedia TV  Application\"},{\"ID\":8108,\"N\":\"cmd\",\"W\":\"C:\\\\Windows\\\\system32\\\\cmd.exe\",\"D\":\"\"},{\"ID\":3768,\"N\":\"conhost\",\"W\":\"\",\"D\":\"\"},{\"ID\":628,\"N\":\"csrss\",\"W\":\"\",\"D\":\"\"},{\"ID\":536,\"N\":\"csrss\",\"W\":\"\",\"D\":\"\"},{\"ID\":1320,\"N\":\"DesktopWatcher\",\"W\":\"\",\"D\":\"DesktopWatcher\"},{\"ID\":9176,\"N\":\"devenv\",\"W\":\"RemoteControlServer (Debugging) - Microsoft Visual Studio\",\"D\":\"Microsoft Visual Studio 2010\"},{\"ID\":2420,\"N\":\"DTLite\",\"W\":\"\",\"D\":\"DAEMON Tools Lite\"},{\"ID\":2124,\"N\":\"dwm\",\"W\":\"\",\"D\":\"\"},{\"ID\":3100,\"N\":\"eclipse\",\"W\":\"\",\"D\":\"\"},{\"ID\":7436,\"N\":\"eclipse\",\"W\":\"\",\"D\":\"\"},{\"ID\":2148,\"N\":\"explorer\",\"W\":\"\",\"D\":\"\"},{\"ID\":3932,\"N\":\"firefox\",\"W\":\"Request Progress\",\"D\":\"Firefox\"},{\"ID\":4760,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":4676,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":7808,\"N\":\"chrome\",\"W\":\"Developer Console - Google Chrome\",\"D\":\"Google Chrome\"},{\"ID\":6220,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":6872,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":4236,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":4956,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":8932,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":8612,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":7108,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":7736,\"N\":\"iexplore\",\"W\":\"\",\"D\":\"Internet Explorer\"},{\"ID\":6308,\"N\":\"iexplore\",\"W\":\"Web Application Starter Project - Windows Internet Explorer\",\"D\":\"Internet Explorer\"},{\"ID\":8016,\"N\":\"iexplore\",\"W\":\"\",\"D\":\"Internet Explorer\"},{\"ID\":4992,\"N\":\"javaw\",\"W\":\"Java - RemoteControl/src/com/scurab/web/remotecontrol/client/presenter/DiskBrowserPresenter.java - Eclipse\",\"D\":\"\"},{\"ID\":1328,\"N\":\"javaw\",\"W\":\"\",\"D\":\"Java(TM) Platform SE binary\"},{\"ID\":6492,\"N\":\"javaw\",\"W\":\"Java - RemoteControl/res/layout/sysmanager_content.xml - Eclipse\",\"D\":\"\"},{\"ID\":2860,\"N\":\"jusched\",\"W\":\"\",\"D\":\"Java(TM) Update Scheduler\"},{\"ID\":728,\"N\":\"lsass\",\"W\":\"\",\"D\":\"\"},{\"ID\":736,\"N\":\"lsm\",\"W\":\"\",\"D\":\"\"},{\"ID\":2940,\"N\":\"miranda32\",\"W\":\"Luc - Offline\",\"D\":\"Miranda IM\"},{\"ID\":1944,\"N\":\"MsDepSvc\",\"W\":\"\",\"D\":\"\"},{\"ID\":996,\"N\":\"MsMpEng\",\"W\":\"\",\"D\":\"\"},{\"ID\":2388,\"N\":\"msseces\",\"W\":\"\",\"D\":\"\"},{\"ID\":2968,\"N\":\"mysqld\",\"W\":\"\",\"D\":\"\"},{\"ID\":1480,\"N\":\"MySQLWorkbench\",\"W\":\"MySQL Workbench\",\"D\":\"MySQL Workbench\"},{\"ID\":3260,\"N\":\"NisSrv\",\"W\":\"\",\"D\":\"\"},{\"ID\":6056,\"N\":\"OSPPSVC\",\"W\":\"\",\"D\":\"\"},{\"ID\":6668,\"N\":\"pageant\",\"W\":\"\",\"D\":\"PuTTY SSH authentication agent\"},{\"ID\":4604,\"N\":\"plugin-container\",\"W\":\"\",\"D\":\"Plugin Container for Firefox\"},{\"ID\":5372,\"N\":\"plugin-container\",\"W\":\"\",\"D\":\"Plugin Container for Firefox\"},{\"ID\":7132,\"N\":\"PSPad\",\"W\":\"Novy2.java\",\"D\":\"PSPad editor\"},{\"ID\":2400,\"N\":\"RAVCpl64\",\"W\":\"\",\"D\":\"\"},{\"ID\":7148,\"N\":\"RemoteControlServer.vshost\",\"W\":\"\",\"D\":\"vshost32.exe\"},{\"ID\":4572,\"N\":\"rundll32\",\"W\":\"\",\"D\":\"Windows host process (Rundll32)\"},{\"ID\":3204,\"N\":\"SearchIndexer\",\"W\":\"\",\"D\":\"\"},{\"ID\":720,\"N\":\"services\",\"W\":\"\",\"D\":\"\"},{\"ID\":2488,\"N\":\"sidebar\",\"W\":\"\",\"D\":\"\"},{\"ID\":396,\"N\":\"smss\",\"W\":\"\",\"D\":\"\"},{\"ID\":1620,\"N\":\"spoolsv\",\"W\":\"\",\"D\":\"\"},{\"ID\":3040,\"N\":\"sqlwriter\",\"W\":\"\",\"D\":\"\"},{\"ID\":3980,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1076,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1432,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":3704,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":2080,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":928,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":852,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1204,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":868,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1048,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1648,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1844,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":4,\"N\":\"System\",\"W\":\"\",\"D\":\"\"},{\"ID\":1856,\"N\":\"taskhost\",\"W\":\"\",\"D\":\"\"},{\"ID\":3016,\"N\":\"TCPSVCS\",\"W\":\"\",\"D\":\"\"},{\"ID\":1160,\"N\":\"TGitCache\",\"W\":\"\",\"D\":\"\"},{\"ID\":5424,\"N\":\"TOTALCMD\",\"W\":\"Total Commander PowerPack 2.0 beta - CLUSTER/BLiZZARD!\",\"D\":\"Total Commander 32 bit\"},{\"ID\":5408,\"N\":\"utorrent-2.0\",\"W\":\"\",\"D\":\"\u00b5Torrent\"},{\"ID\":2592,\"N\":\"vmnat\",\"W\":\"\",\"D\":\"\"},{\"ID\":1020,\"N\":\"vmnetdhcp\",\"W\":\"\",\"D\":\"\"},{\"ID\":2720,\"N\":\"vmware-authd\",\"W\":\"\",\"D\":\"\"},{\"ID\":2868,\"N\":\"vmware-tray\",\"W\":\"\",\"D\":\"VMware Tray Process\"},{\"ID\":2284,\"N\":\"vmware-usbarbitrator\",\"W\":\"\",\"D\":\"\"},{\"ID\":5740,\"N\":\"winamp\",\"W\":\"3. Andrea Bertolini - Sticky (Matan Caspi Remix) (Progressive - DIGITALLY IMPORTED - house, techno, and trance beats for your mind!) - Winamp [Paused]\",\"D\":\"Winamp\"},{\"ID\":608,\"N\":\"wininit\",\"W\":\"\",\"D\":\"\"},{\"ID\":672,\"N\":\"winlogon\",\"W\":\"\",\"D\":\"\"},{\"ID\":4840,\"N\":\"WmiPrvSE\",\"W\":\"\",\"D\":\"\"},{\"ID\":5420,\"N\":\"WmiPrvSE\",\"W\":\"\",\"D\":\"\"},{\"ID\":6156,\"N\":\"WmiPrvSE\",\"W\":\"\",\"D\":\"\"},{\"ID\":4056,\"N\":\"wuauclt\",\"W\":\"\",\"D\":\"\"},{\"ID\":5356,\"N\":\"WUDFHost\",\"W\":\"\",\"D\":\"\"}]";
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

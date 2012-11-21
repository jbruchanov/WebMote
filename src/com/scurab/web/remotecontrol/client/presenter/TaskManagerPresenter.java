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
			p.Name = row.get("Name");
			p.ID = Integer.parseInt(row.get("PID"));
			if(row.containsKey("Title"))
			{
				String s = row.get("Title").trim();
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
			case Activate:
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
//					return "[{\"ID\":4792,\"N\":\"adb\",\"W\":\"\",\"D\":null},{\"ID\":1404,\"N\":\"atieclxx\",\"W\":\"\",\"D\":\"\"},{\"ID\":456,\"N\":\"atiesrxx\",\"W\":\"\",\"D\":\"\"},{\"ID\":1140,\"N\":\"audiodg\",\"W\":\"\",\"D\":\"\"},{\"ID\":2832,\"N\":\"AVerHIDReceiver\",\"W\":\"\",\"D\":\"HIDRec Application\"},{\"ID\":2892,\"N\":\"AVerQuick\",\"W\":\"\",\"D\":\"AVerQuick\"},{\"ID\":1768,\"N\":\"AVerRemote\",\"W\":\"\",\"D\":\"\"},{\"ID\":1796,\"N\":\"AVerScheduleService\",\"W\":\"\",\"D\":\"\"},{\"ID\":3824,\"N\":\"AVerTV\",\"W\":\"AVerTV - Video\",\"D\":\"AVerMedia TV  Application\"},{\"ID\":8108,\"N\":\"cmd\",\"W\":\"C:\\\\Windows\\\\system32\\\\cmd.exe\",\"D\":\"\"},{\"ID\":3768,\"N\":\"conhost\",\"W\":\"\",\"D\":\"\"},{\"ID\":628,\"N\":\"csrss\",\"W\":\"\",\"D\":\"\"},{\"ID\":536,\"N\":\"csrss\",\"W\":\"\",\"D\":\"\"},{\"ID\":1320,\"N\":\"DesktopWatcher\",\"W\":\"\",\"D\":\"DesktopWatcher\"},{\"ID\":9176,\"N\":\"devenv\",\"W\":\"RemoteControlServer (Debugging) - Microsoft Visual Studio\",\"D\":\"Microsoft Visual Studio 2010\"},{\"ID\":2420,\"N\":\"DTLite\",\"W\":\"\",\"D\":\"DAEMON Tools Lite\"},{\"ID\":2124,\"N\":\"dwm\",\"W\":\"\",\"D\":\"\"},{\"ID\":3100,\"N\":\"eclipse\",\"W\":\"\",\"D\":\"\"},{\"ID\":7436,\"N\":\"eclipse\",\"W\":\"\",\"D\":\"\"},{\"ID\":2148,\"N\":\"explorer\",\"W\":\"\",\"D\":\"\"},{\"ID\":3932,\"N\":\"firefox\",\"W\":\"Request Progress\",\"D\":\"Firefox\"},{\"ID\":4760,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":4676,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":7808,\"N\":\"chrome\",\"W\":\"Developer Console - Google Chrome\",\"D\":\"Google Chrome\"},{\"ID\":6220,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":6872,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":4236,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":4956,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":8932,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":8612,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":7108,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":7736,\"N\":\"iexplore\",\"W\":\"\",\"D\":\"Internet Explorer\"},{\"ID\":6308,\"N\":\"iexplore\",\"W\":\"Web Application Starter Project - Windows Internet Explorer\",\"D\":\"Internet Explorer\"},{\"ID\":8016,\"N\":\"iexplore\",\"W\":\"\",\"D\":\"Internet Explorer\"},{\"ID\":4992,\"N\":\"javaw\",\"W\":\"Java - RemoteControl/src/com/scurab/web/remotecontrol/client/presenter/DiskBrowserPresenter.java - Eclipse\",\"D\":\"\"},{\"ID\":1328,\"N\":\"javaw\",\"W\":\"\",\"D\":\"Java(TM) Platform SE binary\"},{\"ID\":6492,\"N\":\"javaw\",\"W\":\"Java - RemoteControl/res/layout/sysmanager_content.xml - Eclipse\",\"D\":\"\"},{\"ID\":2860,\"N\":\"jusched\",\"W\":\"\",\"D\":\"Java(TM) Update Scheduler\"},{\"ID\":728,\"N\":\"lsass\",\"W\":\"\",\"D\":\"\"},{\"ID\":736,\"N\":\"lsm\",\"W\":\"\",\"D\":\"\"},{\"ID\":2940,\"N\":\"miranda32\",\"W\":\"Luc - Offline\",\"D\":\"Miranda IM\"},{\"ID\":1944,\"N\":\"MsDepSvc\",\"W\":\"\",\"D\":\"\"},{\"ID\":996,\"N\":\"MsMpEng\",\"W\":\"\",\"D\":\"\"},{\"ID\":2388,\"N\":\"msseces\",\"W\":\"\",\"D\":\"\"},{\"ID\":2968,\"N\":\"mysqld\",\"W\":\"\",\"D\":\"\"},{\"ID\":1480,\"N\":\"MySQLWorkbench\",\"W\":\"MySQL Workbench\",\"D\":\"MySQL Workbench\"},{\"ID\":3260,\"N\":\"NisSrv\",\"W\":\"\",\"D\":\"\"},{\"ID\":6056,\"N\":\"OSPPSVC\",\"W\":\"\",\"D\":\"\"},{\"ID\":6668,\"N\":\"pageant\",\"W\":\"\",\"D\":\"PuTTY SSH authentication agent\"},{\"ID\":4604,\"N\":\"plugin-container\",\"W\":\"\",\"D\":\"Plugin Container for Firefox\"},{\"ID\":5372,\"N\":\"plugin-container\",\"W\":\"\",\"D\":\"Plugin Container for Firefox\"},{\"ID\":7132,\"N\":\"PSPad\",\"W\":\"Novy2.java\",\"D\":\"PSPad editor\"},{\"ID\":2400,\"N\":\"RAVCpl64\",\"W\":\"\",\"D\":\"\"},{\"ID\":7148,\"N\":\"RemoteControlServer.vshost\",\"W\":\"\",\"D\":\"vshost32.exe\"},{\"ID\":4572,\"N\":\"rundll32\",\"W\":\"\",\"D\":\"Windows host process (Rundll32)\"},{\"ID\":3204,\"N\":\"SearchIndexer\",\"W\":\"\",\"D\":\"\"},{\"ID\":720,\"N\":\"services\",\"W\":\"\",\"D\":\"\"},{\"ID\":2488,\"N\":\"sidebar\",\"W\":\"\",\"D\":\"\"},{\"ID\":396,\"N\":\"smss\",\"W\":\"\",\"D\":\"\"},{\"ID\":1620,\"N\":\"spoolsv\",\"W\":\"\",\"D\":\"\"},{\"ID\":3040,\"N\":\"sqlwriter\",\"W\":\"\",\"D\":\"\"},{\"ID\":3980,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1076,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1432,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":3704,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":2080,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":928,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":852,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1204,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":868,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1048,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1648,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1844,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":4,\"N\":\"System\",\"W\":\"\",\"D\":\"\"},{\"ID\":1856,\"N\":\"taskhost\",\"W\":\"\",\"D\":\"\"},{\"ID\":3016,\"N\":\"TCPSVCS\",\"W\":\"\",\"D\":\"\"},{\"ID\":1160,\"N\":\"TGitCache\",\"W\":\"\",\"D\":\"\"},{\"ID\":5424,\"N\":\"TOTALCMD\",\"W\":\"Total Commander PowerPack 2.0 beta - CLUSTER/BLiZZARD!\",\"D\":\"Total Commander 32 bit\"},{\"ID\":5408,\"N\":\"utorrent-2.0\",\"W\":\"\",\"D\":\"\u00b5Torrent\"},{\"ID\":2592,\"N\":\"vmnat\",\"W\":\"\",\"D\":\"\"},{\"ID\":1020,\"N\":\"vmnetdhcp\",\"W\":\"\",\"D\":\"\"},{\"ID\":2720,\"N\":\"vmware-authd\",\"W\":\"\",\"D\":\"\"},{\"ID\":2868,\"N\":\"vmware-tray\",\"W\":\"\",\"D\":\"VMware Tray Process\"},{\"ID\":2284,\"N\":\"vmware-usbarbitrator\",\"W\":\"\",\"D\":\"\"},{\"ID\":5740,\"N\":\"winamp\",\"W\":\"3. Andrea Bertolini - Sticky (Matan Caspi Remix) (Progressive - DIGITALLY IMPORTED - house, techno, and trance beats for your mind!) - Winamp [Paused]\",\"D\":\"Winamp\"},{\"ID\":608,\"N\":\"wininit\",\"W\":\"\",\"D\":\"\"},{\"ID\":672,\"N\":\"winlogon\",\"W\":\"\",\"D\":\"\"},{\"ID\":4840,\"N\":\"WmiPrvSE\",\"W\":\"\",\"D\":\"\"},{\"ID\":5420,\"N\":\"WmiPrvSE\",\"W\":\"\",\"D\":\"\"},{\"ID\":6156,\"N\":\"WmiPrvSE\",\"W\":\"\",\"D\":\"\"},{\"ID\":4056,\"N\":\"wuauclt\",\"W\":\"\",\"D\":\"\"},{\"ID\":5356,\"N\":\"WUDFHost\",\"W\":\"\",\"D\":\"\"}]";
					return "[{\"Name\":\"adb\",\"PID\":1524},{\"Name\":\"AdminService\",\"PID\":1904,\"Title\":\"\"},{\"Name\":\"Ath_CoexAgent\",\"PID\":2220,\"Title\":\"\"},{\"Name\":\"audiodg\",\"PID\":8748,\"Title\":\"\"},{\"Name\":\"BTHSAmpPalService\",\"PID\":1660,\"Title\":\"\"},{\"Name\":\"BTHSSecurityMgr\",\"PID\":2044,\"Title\":\"\"},{\"Name\":\"BtTray\",\"PID\":4484,\"Title\":\"BtTray\"},{\"Name\":\"BtvStack\",\"PID\":4492,\"Title\":\"Extension Core\"},{\"Name\":\"conhost\",\"PID\":6116,\"Title\":\"\"},{\"Name\":\"csrss\",\"PID\":708,\"Title\":\"\"},{\"Name\":\"csrss\",\"PID\":620,\"Title\":\"\"},{\"Name\":\"daemonu\",\"PID\":3816,\"Title\":\"\"},{\"Name\":\"devmonsrv\",\"PID\":1468,\"Title\":\"\"},{\"Name\":\"dllhost\",\"PID\":5444,\"Title\":\"COM Surrogate\"},{\"Name\":\"Dropbox\",\"PID\":4844,\"Title\":\"Dropbox\"},{\"Name\":\"dwm\",\"PID\":1008,\"Title\":\"\"},{\"Name\":\"eclipse\",\"PID\":6300},{\"Name\":\"eclipse\",\"PID\":3132},{\"Name\":\"EvtEng\",\"PID\":1936,\"Title\":\"\"},{\"Name\":\"explorer\",\"PID\":560,\"Title\":\"Windows Explorer\"},{\"Name\":\"firefox\",\"PID\":5036,\"Title\":\"Firefox\"},{\"Name\":\"FlashPlayerPlugin_11_4_402_287\",\"PID\":7528,\"Title\":\"Adobe Flash Player 11.4 r402\"},{\"Name\":\"FlashPlayerPlugin_11_4_402_287\",\"PID\":9988,\"Title\":\"Adobe Flash Player 11.4 r402\"},{\"Name\":\"glcnd\",\"PID\":6512,\"Title\":\"Windows Reader\"},{\"Name\":\"HeciServer\",\"PID\":1988,\"Title\":\"\"},{\"Name\":\"hkcmd\",\"PID\":4424,\"Title\":\"hkcmd Module\"},{\"Name\":\"chrome\",\"PID\":8372,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":5820,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":4932,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":10108,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":2628,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":6308,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":9372,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":3264,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":4720,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":6644,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":2376,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":6080,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":4948,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":8876,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":3564,\"Title\":\"Google Chrome\"},{\"Name\":\"chrome\",\"PID\":2148,\"Title\":\"Google Chrome\"},{\"Name\":\"iFrmewrk\",\"PID\":4536,\"Title\":\"Intel(R) PROSet/Wireless Framework\"},{\"Name\":\"igfxpers\",\"PID\":4444,\"Title\":\"persistence Module\"},{\"Name\":\"igfxtray\",\"PID\":4356,\"Title\":\"igfxTray Module\"},{\"Name\":\"IpOverUsbSvc\",\"PID\":1448,\"Title\":\"\"},{\"Name\":\"javaw\",\"PID\":8432,\"Title\":\"Java(TM) Platform SE binary\"},{\"Name\":\"javaw\",\"PID\":9840,\"Title\":\"Java(TM) Platform SE binary\"},{\"Name\":\"javaw\",\"PID\":10044,\"Title\":\"Java(TM) Platform SE binary\"},{\"Name\":\"LiveComm\",\"PID\":2336,\"Title\":\"Communications Service\"},{\"Name\":\"LiveComm\",\"PID\":7308,\"Title\":\"Communications Service\"},{\"Name\":\"LMS\",\"PID\":2516,\"Title\":\"\"},{\"Name\":\"lsass\",\"PID\":884,\"Title\":\"\"},{\"Name\":\"MsMpEng\",\"PID\":2184,\"Title\":\"\"},{\"Name\":\"nvvsvc\",\"PID\":308,\"Title\":\"\"},{\"Name\":\"nvvsvc\",\"PID\":1256,\"Title\":\"\"},{\"Name\":\"NvXDSync\",\"PID\":1244,\"Title\":\"\"},{\"Name\":\"obexsrv\",\"PID\":2072,\"Title\":\"\"},{\"Name\":\"pageant\",\"PID\":4172,\"Title\":\"PuTTY SSH authentication agent\"},{\"Name\":\"plugin-container\",\"PID\":9072,\"Title\":\"Plugin Container for Firefox\"},{\"Name\":\"PSPad\",\"PID\":3804,\"Title\":\"PSPad editor\"},{\"Name\":\"RAVCpl64\",\"PID\":4300,\"Title\":\"Realtek HD Audio Manager\"},{\"Name\":\"RegSrvc\",\"PID\":2060,\"Title\":\"\"},{\"Name\":\"rundll32\",\"PID\":4476,\"Title\":\"Windows host process (Rundll32)\"},{\"Name\":\"RuntimeBroker\",\"PID\":2392,\"Title\":\"Runtime Broker\"},{\"Name\":\"SearchIndexer\",\"PID\":1804,\"Title\":\"\"},{\"Name\":\"services\",\"PID\":876,\"Title\":\"\"},{\"Name\":\"smss\",\"PID\":424,\"Title\":\"\"},{\"Name\":\"spoolsv\",\"PID\":1692,\"Title\":\"\"},{\"Name\":\"sqlwriter\",\"PID\":2116,\"Title\":\"\"},{\"Name\":\"svchost\",\"PID\":1040,\"Title\":\"\"},{\"Name\":\"svchost\",\"PID\":1144,\"Title\":\"\"},{\"Name\":\"svchost\",\"PID\":992,\"Title\":\"\"},{\"Name\":\"svchost\",\"PID\":1720,\"Title\":\"\"},{\"Name\":\"svchost\",\"PID\":2400,\"Title\":\"\"},{\"Name\":\"svchost\",\"PID\":1196,\"Title\":\"\"},{\"Name\":\"svchost\",\"PID\":1416,\"Title\":\"\"},{\"Name\":\"svchost\",\"PID\":1076,\"Title\":\"\"},{\"Name\":\"svchost\",\"PID\":2304,\"Title\":\"\"},{\"Name\":\"svchost\",\"PID\":468,\"Title\":\"\"},{\"Name\":\"svchost\",\"PID\":6540,\"Title\":\"\"},{\"Name\":\"System\",\"PID\":4,\"Title\":\"\"},{\"Name\":\"taskeng\",\"PID\":10160,\"Title\":\"\"},{\"Name\":\"taskhostex\",\"PID\":3712,\"Title\":\"Host Process for Windows Tasks\"},{\"Name\":\"TCPSVCS\",\"PID\":2096,\"Title\":\"\"},{\"Name\":\"TGitCache\",\"PID\":5140,\"Title\":\"TortoiseGit status cache\"},{\"Name\":\"TOTALCMD\",\"PID\":6368,\"Title\":\"Total Commander 32 bit\"},{\"Name\":\"TOTALCMD\",\"PID\":5312,\"Title\":\"Total Commander 32 bit\"},{\"Name\":\"UNS\",\"PID\":4240,\"Title\":\"\"},{\"Name\":\"unsecapp\",\"PID\":3312,\"Title\":\"\"},{\"Name\":\"unsecapp\",\"PID\":4728,\"Title\":\"Sink to receive asynchronous callbacks for WMI client application\"},{\"Name\":\"utorrent_2.2.1\",\"PID\":1412,\"Title\":\"\u00b5Torrent\"},{\"Name\":\"vmms\",\"PID\":2356,\"Title\":\"\"},{\"Name\":\"winamp\",\"PID\":8520,\"Title\":\"Winamp\"},{\"Name\":\"wininit\",\"PID\":716,\"Title\":\"\"},{\"Name\":\"winlogon\",\"PID\":772,\"Title\":\"\"},{\"Name\":\"wlanext\",\"PID\":2412,\"Title\":\"\"},{\"Name\":\"WmiPrvSE\",\"PID\":3836,\"Title\":\"\"},{\"Name\":\"WUDFHost\",\"PID\":2928,\"Title\":\"\"},{\"Name\":\"WWAHost\",\"PID\":1052,\"Title\":\"Microsoft WWA Host\"},{\"Name\":\"ZeroConfigService\",\"PID\":2268,\"Title\":\"\"}]";
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

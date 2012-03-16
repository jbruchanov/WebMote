package com.scurab.web.remotecontrol.client.server;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Header;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.FileManagerCommand;
import com.scurab.web.remotecontrol.client.commands.GetApplicationsCommand;
import com.scurab.web.remotecontrol.client.commands.ProcessCommand;
import com.scurab.web.remotecontrol.client.commands.WinLIRCCommand;

public class WebClientDataService extends DataService
{
	public void sendCommand(Command c) throws RequestException
	{
		sendCommand(c,null);
	}
	
	public void sendCommand(Command c, RequestCallback rc) throws RequestException
	{
		if(rc == null)
			rc = mDoNothingCallback;
			
		try
		{
			notify(c.toString()); //works only in ie
		}
		catch(Exception e){}
		VirtualResponse vr = new VirtualResponse();
		if(c instanceof GetApplicationsCommand)
			vr.setResponseText(VirtualResponse.APPS);
		else if(c instanceof WinLIRCCommand)
			vr.setResponseText(VirtualResponse.IR);
		else if(c instanceof ProcessCommand)
			vr.setResponseText(VirtualResponse.PROCS);
		else if(c instanceof FileManagerCommand)
		{
			FileManagerCommand fmc = ((FileManagerCommand) c);
			if(fmc.Root == null)
				vr.setResponseText(VirtualResponse.DISKS);
			else if(fmc.Root.equals("C:\\"))
				vr.setResponseText(VirtualResponse.DISK_C);
			else
				vr.setResponseText(VirtualResponse.DISK_EMPTY);
		}
		rc.onResponseReceived(null, vr);
	}
	
	private RequestCallback mDoNothingCallback = new RequestCallback()
	{
		@Override
		public void onResponseReceived(Request request, Response response)
		{
			
		}
		
		@Override
		public void onError(Request request, Throwable exception)
		{
			
		}
	};
	
	private native void notify(String c)/*-{
		$wnd.parent.onCommand(c);		
	}-*/;
	
	private class VirtualResponse extends Response
	{
		public static final String APPS = "{\"Audio\":[\"Media Center Audio\",\"WinAmp\"],\"MediaCenter\":[\"Media Center\"],\"Picture\":[\"Media Center Pictures\",\"Windows Photo Viewer\"],\"Television\":[\"Avermedia TV\",\"Media Center TV\"],\"Video\":[\"Media Center Video\",\"VLC PLayer\"], \"WinLIRC\":[\"logitech_z680\"]}";
		public static final String IR = "{\"power\":\"power\",\"test\":\"test\",\"input\":\"input\",\"effect\":\"effect\",\"settings\":\"settings\",\"sub+\":\"sub+\",\"sub-\":\"sub-\",\"center+\":\"center+\",\"center-\":\"center-\",\"surround+\":\"surround+\",\"surround-\":\"surround-\",\"vol+\":\"vol+\",\"vol-\":\"vol-\",\"mute\":\"mute\"}";
		public static final String PROCS = "[{\"ID\":5736,\"N\":\"adb\",\"W\":\"\",\"D\":null},{\"ID\":3060,\"N\":\"atieclxx\",\"W\":\"\",\"D\":\"\"},{\"ID\":172,\"N\":\"atiesrxx\",\"W\":\"\",\"D\":\"\"},{\"ID\":1156,\"N\":\"audiodg\",\"W\":\"\",\"D\":\"\"},{\"ID\":3572,\"N\":\"AVerHIDReceiver\",\"W\":\"\",\"D\":\"HIDRec Application\"},{\"ID\":3636,\"N\":\"AVerQuick\",\"W\":\"\",\"D\":\"AVerQuick\"},{\"ID\":1968,\"N\":\"AVerRemote\",\"W\":\"\",\"D\":\"\"},{\"ID\":2000,\"N\":\"AVerScheduleService\",\"W\":\"\",\"D\":\"\"},{\"ID\":3948,\"N\":\"calc\",\"W\":\"Calculator\",\"D\":\"\"},{\"ID\":3964,\"N\":\"calc\",\"W\":\"Calculator\",\"D\":\"\"},{\"ID\":3956,\"N\":\"calc\",\"W\":\"Calculator\",\"D\":\"\"},{\"ID\":540,\"N\":\"csrss\",\"W\":\"\",\"D\":\"\"},{\"ID\":632,\"N\":\"csrss\",\"W\":\"\",\"D\":\"\"},{\"ID\":3908,\"N\":\"devenv\",\"W\":\"RemoteControlServer (Running) - Microsoft Visual Studio\",\"D\":\"Microsoft Visual Studio 2010\"},{\"ID\":3328,\"N\":\"DTLite\",\"W\":\"\",\"D\":\"DAEMON Tools Lite\"},{\"ID\":2072,\"N\":\"dwm\",\"W\":\"\",\"D\":\"\"},{\"ID\":6020,\"N\":\"eclipse\",\"W\":\"\",\"D\":\"\"},{\"ID\":3088,\"N\":\"explorer\",\"W\":\"\",\"D\":\"\"},{\"ID\":5940,\"N\":\"firefox\",\"W\":\"Request Progress\",\"D\":\"Firefox\"},{\"ID\":1048,\"N\":\"FlashUtil11e_ActiveX\",\"W\":\"\",\"D\":\"Adobe\u00ae Flash\u00ae Player Installer/Uninstaller 11.1 r102\"},{\"ID\":1656,\"N\":\"httpd\",\"W\":\"\",\"D\":\"Apache HTTP Server\"},{\"ID\":624,\"N\":\"httpd\",\"W\":\"\",\"D\":\"Apache HTTP Server\"},{\"ID\":2544,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":3792,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":4968,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":3268,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":4640,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":5540,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":3216,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":4440,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":3888,\"N\":\"chrome\",\"W\":\"Android Remote Control - Google Chrome\",\"D\":\"Google Chrome\"},{\"ID\":5556,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":4680,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":1108,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":6116,\"N\":\"chrome\",\"W\":\"\",\"D\":\"Google Chrome\"},{\"ID\":1892,\"N\":\"iexplore\",\"W\":\"\",\"D\":\"Internet Explorer\"},{\"ID\":6036,\"N\":\"iexplore\",\"W\":\"Web Application Starter Project - Windows Internet Explorer\",\"D\":\"Internet Explorer\"},{\"ID\":5544,\"N\":\"iexplore\",\"W\":\"\",\"D\":\"Internet Explorer\"},{\"ID\":944,\"N\":\"javaw\",\"W\":\"\",\"D\":\"Java(TM) Platform SE binary\"},{\"ID\":5352,\"N\":\"javaw\",\"W\":\"Debug - RemoteControl/src/com/scurab/web/remotecontrol/client/server/WebClientDataService.java - Eclipse\",\"D\":\"\"},{\"ID\":4872,\"N\":\"jucheck\",\"W\":\"\",\"D\":\"\"},{\"ID\":3648,\"N\":\"jusched\",\"W\":\"\",\"D\":\"Java(TM) Update Scheduler\"},{\"ID\":732,\"N\":\"lsass\",\"W\":\"\",\"D\":\"\"},{\"ID\":740,\"N\":\"lsm\",\"W\":\"\",\"D\":\"\"},{\"ID\":3804,\"N\":\"miranda32\",\"W\":\"\",\"D\":\"Miranda IM\"},{\"ID\":1428,\"N\":\"MsDepSvc\",\"W\":\"\",\"D\":\"\"},{\"ID\":1004,\"N\":\"MsMpEng\",\"W\":\"\",\"D\":\"\"},{\"ID\":3300,\"N\":\"msseces\",\"W\":\"\",\"D\":\"\"},{\"ID\":1380,\"N\":\"mysqld\",\"W\":\"\",\"D\":\"\"},{\"ID\":2680,\"N\":\"NisSrv\",\"W\":\"\",\"D\":\"\"},{\"ID\":5364,\"N\":\"notepad\",\"W\":\"header - Notepad\",\"D\":\"Notepad\"},{\"ID\":5564,\"N\":\"PaintDotNet\",\"W\":\"emulator_horizontal.png - Paint.NET v3.5.10\",\"D\":\"\"},{\"ID\":436,\"N\":\"plugin-container\",\"W\":\"\",\"D\":\"Plugin Container for Firefox\"},{\"ID\":5484,\"N\":\"PSPad\",\"W\":\"webclient_content.php\",\"D\":\"PSPad editor\"},{\"ID\":3308,\"N\":\"RAVCpl64\",\"W\":\"\",\"D\":\"\"},{\"ID\":5860,\"N\":\"RemoteControlServer.vshost\",\"W\":\"Remote Control Server\",\"D\":\"vshost32.exe\"},{\"ID\":4672,\"N\":\"rundll32\",\"W\":\"\",\"D\":\"Windows host process (Rundll32)\"},{\"ID\":4032,\"N\":\"SearchIndexer\",\"W\":\"\",\"D\":\"\"},{\"ID\":724,\"N\":\"services\",\"W\":\"\",\"D\":\"\"},{\"ID\":3352,\"N\":\"sidebar\",\"W\":\"\",\"D\":\"\"},{\"ID\":1400,\"N\":\"Skype\",\"W\":\"Skype\u2122\",\"D\":\"Skype \"},{\"ID\":396,\"N\":\"smss\",\"W\":\"\",\"D\":\"\"},{\"ID\":1584,\"N\":\"spoolsv\",\"W\":\"\",\"D\":\"\"},{\"ID\":1920,\"N\":\"sqlwriter\",\"W\":\"\",\"D\":\"\"},{\"ID\":4304,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1312,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":2988,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":936,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1076,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":264,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1060,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":2024,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":464,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":860,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1616,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1460,\"N\":\"svchost\",\"W\":\"\",\"D\":\"\"},{\"ID\":4,\"N\":\"System\",\"W\":\"\",\"D\":\"\"},{\"ID\":2984,\"N\":\"taskhost\",\"W\":\"\",\"D\":\"\"},{\"ID\":1940,\"N\":\"TCPSVCS\",\"W\":\"\",\"D\":\"\"},{\"ID\":1288,\"N\":\"TGitCache\",\"W\":\"\",\"D\":\"\"},{\"ID\":3520,\"N\":\"TOTALCMD\",\"W\":\"Total Commander\",\"D\":\"Total Commander 32 bit\"},{\"ID\":2140,\"N\":\"vmnat\",\"W\":\"\",\"D\":\"\"},{\"ID\":2284,\"N\":\"vmnetdhcp\",\"W\":\"\",\"D\":\"\"},{\"ID\":2200,\"N\":\"vmware-authd\",\"W\":\"\",\"D\":\"\"},{\"ID\":3668,\"N\":\"vmware-tray\",\"W\":\"\",\"D\":\"VMware Tray Process\"},{\"ID\":2060,\"N\":\"vmware-usbarbitrator\",\"W\":\"\",\"D\":\"\"},{\"ID\":4088,\"N\":\"winamp\",\"W\":\"Progressive www.di.fm\",\"D\":\"Winamp\"},{\"ID\":612,\"N\":\"wininit\",\"W\":\"\",\"D\":\"\"},{\"ID\":4976,\"N\":\"winlirc\",\"W\":\"\",\"D\":null},{\"ID\":676,\"N\":\"winlogon\",\"W\":\"\",\"D\":\"\"},{\"ID\":2404,\"N\":\"wuauclt\",\"W\":\"\",\"D\":\"\"},{\"ID\":3856,\"N\":\"xampp-control\",\"W\":\"XAMPP Control Panel Application\",\"D\":null}]";
		public static final String DISKS = "[{\"N\":\"C:\\\\\",\"T\":1},{\"N\":\"D:\\\\\",\"T\":1},{\"N\":\"F:\\\\\",\"T\":1},{\"N\":\"L:\\\\\",\"T\":1},{\"N\":\"R:\\\\\",\"T\":2},{\"N\":\"W:\\\\\",\"T\":2},{\"N\":\"X:\\\\\",\"T\":2}]";
		public static final String DISK_C = "[{\"N\":\"..\",\"T\":-1},{\"N\":\"$Recycle.Bin\",\"T\":10},{\"N\":\"AMD\",\"T\":10},{\"N\":\"Documents and Settings\",\"T\":10},{\"N\":\"MSOCache\",\"T\":10},{\"N\":\"msysgit\",\"T\":10},{\"N\":\"PerfLogs\",\"T\":10},{\"N\":\"Program Files\",\"T\":10},{\"N\":\"Program Files (x86)\",\"T\":10},{\"N\":\"ProgramData\",\"T\":10},{\"N\":\"Recovery\",\"T\":10},{\"N\":\"System Volume Information\",\"T\":10},{\"N\":\"Temporary Files\",\"T\":10},{\"N\":\"Users\",\"T\":10},{\"N\":\"Windows\",\"T\":10},{\"N\":\"xampp\",\"T\":10},{\"N\":\".rnd\",\"T\":20}]";
		public static final String DISK_EMPTY = "[{\"N\":\"..\",\"T\":-1}]";
		public String mResponse = "";
		@Override
		public String getHeader(String header)
		{
			return null;
		}

		@Override
		public Header[] getHeaders()
		{
			return null;
		}

		@Override
		public String getHeadersAsString()
		{
			return null;
		}

		@Override
		public int getStatusCode()
		{
			return 0;
		}

		@Override
		public String getStatusText()
		{
			return null;
		}

		@Override
		public String getText()
		{
			return mResponse;
		}
		
		public void setResponseText(String s)
		{
			mResponse = s;
		}
	}
}

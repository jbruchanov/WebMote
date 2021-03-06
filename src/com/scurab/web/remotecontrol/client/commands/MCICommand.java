package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONObject;

public class MCICommand extends Command
{

	private static final String OPEN_DVD_TEMPLATE = "open %1$s: type CDAudio alias drive%1$s;set drive%1$s door open";
	private static final String CLOSE_DVD_TEMPLATE = "open %1$s: type CDAudio alias drive%1$s;set drive%1$s door closed";
	
	public void closeCD(String drive)
	{
		if(drive.length() > 1)
			drive = drive.substring(0,1);
		this.setMethod(CLOSE_DVD_TEMPLATE.replace("%1$s", drive));
	}
	
	public void  openCD(String drive)
	{
		if(drive.length() > 1)
			drive = drive.substring(0,1);
		this.setMethod(OPEN_DVD_TEMPLATE.replace("%1$s", drive));
	}
	
	@Override
	protected JSONObject getJsonObject()
	{		
		JSONObject jso = super.getJsonObject();
		return jso;
	}
	
	@Override
	protected String getCommandName()
	{
		return "MCICommand";
	}

}

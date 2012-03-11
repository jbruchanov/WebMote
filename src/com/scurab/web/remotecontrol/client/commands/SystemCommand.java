package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class SystemCommand extends Command
{

	public String Operation = null;
	public final static String OPERATION_KEY = "Operation";
	public int Delay = 0;
	public final static String DELAY_KEY = "Delay";
	public boolean Force = true;
	public final static String FORCE_KEY = "Force";

	public void setDelay(int delay)
	{
		Delay = delay;
	}

	public void setForce(boolean force)
	{
		Force = force;
	}

	public void reboot()
	{
		Operation = "Reboot";
	}

	public void shutdown()
	{
		Operation = "Shutdown";
	}

	public void abort()
	{
		Operation = "Abort";
	}

	public void hibernate()
	{
		Operation = "Hibernate";
	}

	public void turnMonitorOn()
	{
		Operation = "MonitorTurnOn";
	}

	public void turnMonitorOff()
	{
		Operation = "MonitorTurnOff";
	}

	@Override
	protected String getCommandName()
	{
		return "SystemCommand";
	}
	
	@Override
	protected JSONObject getJsonObject()
	{
		JSONObject jso = super.getJsonObject();
		jso.put(OPERATION_KEY, new JSONString(Operation));
		jso.put(DELAY_KEY, new JSONNumber(Delay));
		jso.put(FORCE_KEY, JSONBoolean.getInstance(Force));
		return jso;
	}
	
	
}

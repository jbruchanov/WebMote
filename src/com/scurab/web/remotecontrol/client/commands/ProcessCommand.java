package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class ProcessCommand extends Command
{

	public String MethodParameter = null;
	public final static String METHODPARAMETER_KEY = "MethodParameter";
	public String FileLocation = null;
	public final static String FILELOCATION_KEY = "FileLocation";
	public String StartParameters = null;
	public final static String STARTPARAMETERS_KEY = "StartParameters";
	public Integer ProcessID = null;
	public final static String PROCESSID_KEY = "ProcessID";

	private static String GET_COMMAND = "Get";
	private static String RUN_COMMAND = "Run";
	private static String KILL_COMMAND = "Kill";
	private static String ACTIVATE_COMMAND = "Activate";
	
	@Override
	protected String getCommandName()
	{
		return "ProcessCommand";
	}
	
	protected JSONObject getJsonObject()
	{		
		JSONObject jso = super.getJsonObject();
		jso.put(METHODPARAMETER_KEY, new JSONString(MethodParameter));		
		jso.put(FILELOCATION_KEY, new JSONString(FileLocation));
		jso.put(STARTPARAMETERS_KEY, new JSONString(StartParameters));
		if(ProcessID != null)
			jso.put(PROCESSID_KEY, new JSONNumber(ProcessID));		
		return jso;
	}

	public void Get()
	{
		Method = GET_COMMAND;
	}

	public void Run(String fileLocation, String startParameters)
	{
		Method = RUN_COMMAND;
		this.FileLocation = fileLocation;
		this.StartParameters = startParameters;
	}

	public void Kill(int processId)
	{
		Method = KILL_COMMAND;
		ProcessID = processId;
	}

	public void ActivateProcess(int processId)
	{
		Method = ACTIVATE_COMMAND;
		ProcessID = processId;
	}
	
}

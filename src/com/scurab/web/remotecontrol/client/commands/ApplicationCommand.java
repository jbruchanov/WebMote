package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class ApplicationCommand extends Command
{
	private String mApplication;
	private String mMethodParameter;
	private AppType mApplicationType;
	private String mCommand;// appType

	public enum AppType
	{
		Television, Audio, Video, Picture, MediaCenter
	}

	private static final String APP_KEY = "Application";
	private static final String APP_TYPE_KEY = "ApplicationType";
	private static final String METHODMAPARM_KEY = "MethodParameter";

	public ApplicationCommand(String appName)
	{
		super();
		setApplication(appName);
	}

	public ApplicationCommand(String appName, AppType type)
	{
		super();
		setApplication(appName);
		setApplicationType(type);
	}

	@Override
	protected JSONObject getJsonObject()
	{
		JSONObject jso = super.getJsonObject();
		jso.put(APP_KEY, new JSONString(mApplication));
		jso.put(APP_TYPE_KEY, new JSONString(mApplicationType.name()));
		if (mMethodParameter != null)
			jso.put(METHODMAPARM_KEY, new JSONString(mMethodParameter));
		return jso;
	}

	public String getApplication()
	{
		return mApplication;
	}

	public void setApplication(String application)
	{
		mApplication = application;
	}

	public String getMethodParameter()
	{
		return mMethodParameter;
	}

	public void setMethodParameter(String methodParameter)
	{
		mMethodParameter = methodParameter;
	}

	@Override
	public String getCommand()
	{
		return "ApplicationCommand";
	}

	public AppType getApplicationType()
	{
		return mApplicationType;
	}

	public void setApplicationType(AppType applicationType)
	{
		mApplicationType = applicationType;
	}
}

package com.scurab.web.remotecontrol.client.commands;

import java.util.HashMap;
import java.util.List;


public class InfoCommand extends Command
{
	private String mComputerName;
	private HashMap<String, List<String>> mApps;
	private String mAddress;
	private String[] mMACAddresses;
	private String mPathSeparator;
	private String mPlatform;
	
	public InfoCommand()
	{
		setMethod(GET);
	}

	public String getComputerName()
	{
		return mComputerName;
	}

	public void setComputerName(String computerName)
	{
		mComputerName = computerName;
	}

	public HashMap<String, List<String>> getApplications()
	{
		return mApps;
	}

	public void setApplications(HashMap<String, List<String>> apps)
	{
		mApps = apps;
	}

	public String getAddress()
	{
		return mAddress;
	}

	public void setAddress(String address)
	{
		mAddress = address;
	}

	public String[] getMACAddresses()
	{
		return mMACAddresses;
	}

	public void setMACAddresses(String[] mACAddresses)
	{
		mMACAddresses = mACAddresses;
	}

	@Override
	protected String getCommand()
	{
		return "InfoCommand";
	}

    public String getPathSeparator() {
        return mPathSeparator;
    }

    public void setPathSeparator(String pathSeparator) {
        mPathSeparator = pathSeparator;
    }

    public String getPlatform() {
        return mPlatform;
    }

    public void setPlatform(String platform) {
        mPlatform = platform;
    }

}

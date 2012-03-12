package com.scurab.web.remotecontrol.client.tools;

public class StringUtils
{
	public static String getParentDir(String s)
	{
		if(s == null)
			return null;
		
		String data[] = s.split("\\\\"); // '\'
		String result = "";
		for(int i = 0;i<data.length-1;i++)
		{
			if(result.length() > 0)
				result = result + data[i] + "\\";
			else
				result = data[i] + "\\";
		}
		
		if(result.trim().length() == 0)
			result = null;
		else
		{
			if(data.length  > 2) //if data.length 2 => C:\Data and result must be C:\ (keep \)
				result = result.substring(0,result.length()-1);
		}
		return result;
	}
	
	public static String get2DecimalValue(int value)
	{
		if(value < 10)
			return "0" + value;
		else
			return String.valueOf(value);
	}
	
	public static boolean isEmpty(String v)
	{
		return (!(v != null && v.trim().length() != 0));
	}
}

package com.scurab.web.remotecontrol.client.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonSimpleParser
{
	
	private static int state = 0;
	private static final int STATE_START = 0;
	private static final int STATE_PARSINGSTARTITEM = 1;
	private static final int STATE_PARSINGSTARTED = 2;
	private static final int STATE_PARSINGKEY = 3;
	private static final int STATE_PARSEDKEY = 4;
	private static final int STATE_PARSINGVALUE = 5;
	private static final int STATE_PARSEDVALUE = 6;
	private static final int STATE_PARSINGVALUESTRING = 7;
	private static final int STATE_PARSINGVALUELIST = 8;
	private static final int STATE_STARTPARSINGVALUELIST = 98;
	
	private static final int STATE_ENDED = 9;
	
	
	public static List<HashMap<String,String>> parse(String text)
	{
		state = STATE_START;
		List<HashMap<String,String>> data = new ArrayList<HashMap<String, String>>();
		HashMap<String,String> currentItem = null;
		char[] chars = text.toCharArray();
		
		StringBuilder current = new StringBuilder();
		String key = null;
		String value = null;
		
		for(char c : chars)
		{
			switch(state)
			{
				case STATE_START:
				{
					if(isWhitespace(c)) continue;
					if(c == '[') state = STATE_PARSINGSTARTITEM;
					if(c == '{') 
					{
						currentItem = new HashMap<String, String>();
						state = STATE_PARSINGSTARTED;
					}
				}
				break;
				case STATE_PARSINGSTARTITEM:
				{
					if(isWhitespace(c)) continue;
					else if(c == '{') 
					{
						currentItem = new HashMap<String, String>();
						state = STATE_PARSINGSTARTED;
					}
					else if(c == '\"')
					{
						state = STATE_PARSINGKEY;
					}
					
				}	
				break;
				case STATE_PARSINGSTARTED:
				{
					if(isWhitespace(c)) continue;
					if(c == '\"')
					{
						state = STATE_PARSINGKEY;
						current.setLength(0);
					}
				}
				break;
				case STATE_PARSINGKEY:
				{					
					if(c == '\"')
					{
						state = STATE_PARSEDKEY;
						key = current.toString();
						current.setLength(0);
					}
					else
					{
						current.append(c);
					}
				}
				break;
				case STATE_PARSEDKEY:
				{
					if(isWhitespace(c)) continue;
					if(c == ':')
					{
						state = STATE_PARSINGVALUE;
						current.setLength(0);
					}
				}
				break;
				case STATE_PARSINGVALUE:
				{
					if(c == '}')
					{
						value = current.toString();						
						current.setLength(0);			
						currentItem.put(key, value);
						data.add(currentItem);
						state = STATE_PARSEDVALUE;					
					}
					else if(c == ',')
					{
						value = current.toString();						
						current.setLength(0);			
						currentItem.put(key, value);						
						state = STATE_PARSINGSTARTED;
					}
					else if(c == '\"')
					{
						state = STATE_PARSINGVALUESTRING;
					}
					else if(c == '[')
					{
						current.append(c);
						state = STATE_PARSINGVALUELIST;
					}
					else if(!isWhitespace(c))
						current.append(c);
				}
				break;
				case STATE_PARSINGVALUELIST:
				{
					if(c == ']')
					{
						current.append(c);
						state = STATE_PARSINGVALUE;
					}				
					else
						current.append(c);
				}
				break;
				case STATE_PARSINGVALUESTRING:
				{
					if(c != '\"')
						current.append(c);
					else
						state = STATE_PARSINGVALUE;
				}
				break;
				case STATE_PARSEDVALUE:
				{
					if(isWhitespace(c)) continue;
					if(c == ']')
						state = STATE_ENDED;
					else if(c == '{')
					{
						state = STATE_PARSINGSTARTED;
						currentItem = new HashMap<String, String>();
					}
				}
				break;
				case STATE_ENDED:
					break;
			}
		}
		return data;
	}	
	
	public static HashMap<String, List<String>> parseApps(String text) throws Exception
	{
		HashMap<String, List<String>> result = new HashMap<String, List<String>>();
		List<String> list = new ArrayList<String>();
		char[] chars = text.toCharArray();
		
		StringBuilder current = new StringBuilder();
		String key = null;
		String value = null;
		
		for(char c : chars)
		{
			switch(state)
			{
				case STATE_START:
				{
					if(c == '{')
						state = STATE_PARSINGSTARTED;
				}				
				break;
				case STATE_PARSINGSTARTED:
				{
					if(isWhitespace(c))continue;
					if(c == '\"')
						state = STATE_PARSINGKEY;
					else if(c ==  ',') //next item
					{
						continue;
					}
				}
				break;
				case STATE_PARSINGKEY:
				{
					if(c == '\"')
						state = STATE_PARSEDKEY;
					else
						current.append(c);
				}
				break;
				case STATE_PARSEDKEY:
				{					
					key = current.toString();
					current.setLength(0);
					if(c == ':')
						state = STATE_PARSINGVALUELIST;
					else
						throw new Exception("Not implemented state!");
				}
				break;
				case STATE_STARTPARSINGVALUELIST:
				{
					if(isWhitespace(c))continue;
					if(c == '[')
						state = STATE_PARSINGVALUELIST;
				}
				break;
				case STATE_PARSINGVALUELIST:
				{
					if(isWhitespace(c))continue;
					if(c == '\"')
						state = STATE_PARSINGVALUE;
					else if(c == ',')
					{
						continue; //next item in lis
					}
					else if(c == ']')//list is empty
					{
						result.put(key, list);
						list = new ArrayList<String>();
						key = "";
						state = STATE_PARSINGSTARTED;
					}
				}				
				break;
				case STATE_PARSINGVALUE:
				{
					if(c == '\"')
					{
						list.add(current.toString());
						current.setLength(0);
						state = STATE_PARSINGVALUELIST;
					}
					else 
						current.append(c);
				}				
				break;
			}
		}
		return result;
	}
	
	private static boolean isWhitespace(char c)
	{
		return c == ' ' || c == '\n' || c == '\t';
	}
}

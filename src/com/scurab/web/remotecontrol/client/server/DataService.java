package com.scurab.web.remotecontrol.client.server;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.scurab.web.remotecontrol.client.commands.Command;

public class DataService
{
	public void sendCommand(Command c) throws RequestException
	{
		sendCommand(c,null);
	}
	
	public void sendCommand(Command c, RequestCallback rc) throws RequestException
	{
		if(rc == null)
			rc = mDoNothingCallback;
			
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST,GWT.getHostPageBaseURL());
		rb.setHeader("Content-Type","application/x-www-form-urlencoded");		
		rb.sendRequest(c.toString(), rc);		
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
}

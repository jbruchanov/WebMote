package com.scurab.web.remotecontrol.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.components.CommandableClickHandlerWrapper;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.InlineLabel;

public class JoyPad extends AbstractView
{
	public static final String COMMAND_LEFT = "Left";
	public static final String COMMAND_UP = "Up";
	public static final String COMMAND_DOWN = "Down";
	public static final String COMMAND_RIGHT = "Right";
	public static final String COMMAND_CENTER = "Center";
	
	private static JoyPadUiBinder uiBinder = GWT.create(JoyPadUiBinder.class);
	@UiField InlineLabel btnLeft;
	@UiField InlineLabel btnRight;
	@UiField InlineLabel btnUp;
	@UiField InlineLabel btnDown;
	@UiField InlineLabel btnCenter;

	CommandableClickHandlerWrapper btnL = null;
	CommandableClickHandlerWrapper btnR = null;
	CommandableClickHandlerWrapper btnU = null;
	CommandableClickHandlerWrapper btnD = null;
	CommandableClickHandlerWrapper btnC = null;
	List<IsCommandableClickHandler> mList = null;
			
	interface JoyPadUiBinder extends UiBinder<Widget, JoyPad>
	{
	}

	public JoyPad()
	{
		initWidget(uiBinder.createAndBindUi(this));
		btnL = CommandableClickHandlerWrapper.asCommandableClickObject(btnLeft, COMMAND_LEFT);
		btnR = CommandableClickHandlerWrapper.asCommandableClickObject(btnRight, COMMAND_RIGHT);
		btnU = CommandableClickHandlerWrapper.asCommandableClickObject(btnUp, COMMAND_UP);
		btnD = CommandableClickHandlerWrapper.asCommandableClickObject(btnDown, COMMAND_DOWN);
		btnC = CommandableClickHandlerWrapper.asCommandableClickObject(btnCenter, COMMAND_CENTER);
		
		mList = new ArrayList<IsCommandableClickHandler>();
		mList.addAll(Arrays.asList(new IsCommandableClickHandler[]{btnL,btnU,btnR,btnD,btnC}));
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return mList;
	}
	
	
}

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
	public static final Object COMMAND_LEFT = "Left";
	public static final Object COMMAND_UP = "Up";
	public static final Object COMMAND_DOWN = "Down";
	public static final Object COMMAND_RIGHT = "Right";
	public static final Object COMMAND_CENTER = "Center";
	
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
		btnL = CommandableClickHandlerWrapper.asCommandableClickObject(btnLeft, "Left");
		btnR = CommandableClickHandlerWrapper.asCommandableClickObject(btnRight, "Right");
		btnU = CommandableClickHandlerWrapper.asCommandableClickObject(btnUp, "Up");
		btnD = CommandableClickHandlerWrapper.asCommandableClickObject(btnDown, "Down");
		btnC = CommandableClickHandlerWrapper.asCommandableClickObject(btnCenter, "Center");
		
		mList = new ArrayList<IsCommandableClickHandler>();
		mList.addAll(Arrays.asList(new IsCommandableClickHandler[]{btnL,btnU,btnR,btnD,btnC}));
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return mList;
	}
	
	
}

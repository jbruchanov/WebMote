package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.scurab.web.remotecontrol.client.R;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.KeyboardCommand;
import com.scurab.web.remotecontrol.client.commands.MouseCommand;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.DesktopView;

public class DesktopViewPresenter extends BaseControlPresenter
{
	private DesktopView mDisplay = null;
	private Timer mTimer = null;
	private int mRepeaterCounter = 0;
	private int mLastX = 0;
	private int mLastY = 0;
	private static final int MOUSE_LIMIT = 250;
	private static long mLastMouseCommandSendTime = 0;
	private static final String WIDTH_KEY = "%WIDTH_KEY%";
	private static final String HEIGHT_KEY = "%HEIGHT_KEY%";
	private static final String SCALE_KEY = "%SCALE_KEY%";
	private static final String COMPRESS_KEY = "%COMPRESS_KEY%";
	private static final String PIN_KEY = "%PIN_KEY%";
	private static final String ITER_KEY = "%ITER_KEY%";
	private static final String TEMPLATE = "DesktopView.jpg?w=" + WIDTH_KEY + 
														   "&h=" + HEIGHT_KEY + 
														   "&scale=" + SCALE_KEY + 
														   "&compress="+ COMPRESS_KEY + 
														   "&PIN=" + PIN_KEY + 
														   "&iter=" + ITER_KEY;
	
	private static final String TEMPLATE_WEBCLIENT = "desktop/desktop%s.jpg";
	
	public DesktopViewPresenter(DataService dataService, HandlerManager eventBus, DesktopView display)
	{
		super(dataService, eventBus, display);
		mDisplay = display;
		bind();
	}
	
	private void bind()	
	{
		mDisplay.getTglOnOff().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onToggleOnOffClick(mDisplay.getTglOnOff().getValue());
			}
		});
		
		mDisplay.getImage().addTouchStartHandler(new TouchStartHandler()
		{
			@Override
			public void onTouchStart(TouchStartEvent event)
			{
				Touch t = event.getTargetTouches().get(0);
				DesktopViewPresenter.this.onStartTouchMove(t);
			}
		});
		
		mDisplay.getImage().addTouchMoveHandler(new TouchMoveHandler()
		{
			@Override
			public void onTouchMove(TouchMoveEvent event)
			{
				Touch t = event.getTargetTouches().get(0);
				DesktopViewPresenter.this.onTouchMove(t);
			}
		});
		
		mDisplay.getImage().setHeight((Window.getClientHeight() - 130) + "px");
	}	
	
	protected void onStartTouchMove(Touch t)
	{
		mLastX = t.getScreenX();
		mLastY = t.getScreenY();
	}
	protected void onTouchMove(Touch t)
	{
		long now = System.currentTimeMillis();
		if (now > mLastMouseCommandSendTime + MOUSE_LIMIT)
		{
			MouseCommand mc = new MouseCommand();
			int x = t.getScreenX();
			int y = t.getScreenY();
			mc.setX(x - mLastX);
			mc.setY(y - mLastY);
			if(mc.getX() != 0 || mc.getY() != 0){
				onSendCommand(mc);
			}
			mLastX = x;
			mLastY = y;
			mLastMouseCommandSendTime = now;
		}
	}
	
	protected void onToggleOnOffClick(boolean value)
	{
		if(value)
			startTimer();
		else
			stopTimer();
	}
	
	protected void startTimer()
	{
		mTimer = new Timer()
		{
			@Override
			public void run()
			{
				onReloadDesktop();	
			}
		};
		mTimer.scheduleRepeating(1000);		
	}
	
	protected void stopTimer()
	{
		mTimer.cancel();
		mTimer = null;		
	}
	
	private void onReloadDesktop()
	{
		String reqUrl = "";
		if(R.WebClientDemo)
		{
			reqUrl = TEMPLATE_WEBCLIENT.replace("%s", String.valueOf((mRepeaterCounter++)%17));
		}
		else
		{
			reqUrl = TEMPLATE.replace(WIDTH_KEY, String.valueOf(mDisplay.getImage().getWidth()));
			reqUrl = reqUrl.replace(HEIGHT_KEY, String.valueOf(mDisplay.getImage().getHeight()));
			reqUrl = reqUrl.replace(SCALE_KEY, String.valueOf(0.8));
			reqUrl = reqUrl.replace(COMPRESS_KEY,String.valueOf(80));
			reqUrl = reqUrl.replace(PIN_KEY, RemoteControl.getPIN());
			reqUrl = reqUrl.replace(ITER_KEY, String.valueOf(mRepeaterCounter++));		
		}
		mDisplay.getImage().setUrl(reqUrl);
	}

	@Override
	protected Command getCommand(String command)
	{
		Command c = null;
		if("0x1B".equals(command))//ESCAPE
		{
			KeyboardCommand kc = new KeyboardCommand();
			kc.setKeyCode(command);
			c = kc;
		}
		else
		{
			MouseCommand mc = new MouseCommand();
			mc.setMethod(command);
			c = mc;
		}
		return c;
	}

	@Override
	public String getName()
	{
		return "DesktopViewPresenter";
	}

}

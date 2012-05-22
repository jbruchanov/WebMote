package com.scurab.web.remotecontrol.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;
import com.scurab.web.remotecontrol.client.tools.RCMath;

public class VolumeControl extends AbstractView implements HasValue<Integer>, HasValueChangeHandlers<Integer>
{

	private static VolumeControlUiBinder uiBinder = GWT.create(VolumeControlUiBinder.class);
//	@UiField Label lblInfo;
	@UiField Image imgPointer;
	@UiField Image imgBackground;
	@UiField Label lblVolume;
	
	private int centerX;
	private int centerY;
	private int lenFromCenter = centerX - 85;//dont change it
	private int imgPointerHalfSize = 0;
	private int leftOffset = 0;
	private int topOffset = 0;
	private int mMin = 30;
	private int mMax = 330;
	private int mLastAngle = 0;
	private HandlerManager mHandler = new HandlerManager(this);
	private boolean mIsPCInternetExplorer = false;
	
	private static final int[] MSIE_CENTER_OFFSET = new int[] {12,15};
	private static final int[] CENTER_OFFSET = new int[] {-3,0}; 

	interface VolumeControlUiBinder extends UiBinder<Widget, VolumeControl>
	{
		
	}

	public VolumeControl()
	{
		initWidget(uiBinder.createAndBindUi(this));
		String agent = RemoteControl.getUserAgent().toLowerCase();
		//mIsPCInternetExplorer = agent.contains("msie") && !agent.contains("iemobile");
		imgPointer.setVisible(false);
//		lblInfo.setVisible(false);
		Window.addResizeHandler(new ResizeHandler()
		{
			@Override
			public void onResize(ResizeEvent event)
			{
				handleMove(mLastAngle);//refresh pointer if there is winresize	
			}
		});
	}
	
	@Override
	protected void onLoad()
	{
		super.onLoad();
		Timer t = new Timer(){@Override public void run(){init();}};
		t.schedule(500);
		init();
		
	}
	
	private void init()
	{		
		int height = imgBackground.getHeight();
		topOffset = imgBackground.getAbsoluteTop(); 
		centerY = topOffset + (height / 2);
		centerX = Window.getClientWidth() / 2;
		if(mIsPCInternetExplorer)
		{
			centerX -= MSIE_CENTER_OFFSET[0];
			centerY -= MSIE_CENTER_OFFSET[1];
		}
		else
		{
			centerX -= CENTER_OFFSET[0];
			centerY -= CENTER_OFFSET[1];
		}
		imgPointerHalfSize = imgPointer.getWidth() / 2;
		Timer t = new Timer(){@Override public void run()
		{
			handleMove(30,false); //to set value to 0
			imgPointer.setVisible(true);			
		}};		
		handleMove(30,false); 
		imgPointer.setVisible(true);
		t.schedule(1000);				
	}
	
		
	int i = 0;
	@UiHandler("imgPointer")
	void onImgPointerTouchMove(TouchMoveEvent event) {
		handleMoveTouchMoveEvent(event);
	}
	
	@UiHandler("imgBackground")
	void onImgBackgroundTouchMove(TouchMoveEvent event) {
		handleMoveTouchMoveEvent(event);
	}
	
	@UiHandler("lblVolume")
	void onLblVolumeTouchMove(TouchMoveEvent event) {
		handleMoveTouchMoveEvent(event);
	}
	private void handleMoveTouchMoveEvent(TouchMoveEvent event)
	{
		event.preventDefault();
		if(event.getTouches().length() == 1)
		{
			int x = event.getTouches().get(0).getClientX();
			int y = event.getTouches().get(0).getClientY();
			handleMove(x, y);
		}
		
	}
	private void handleMove(int x, int y)
	{
		double angle = RCMath.getAngle(x, y, getCenterX(), centerY);
		if(angle < mMin) angle = mMin;
		if(angle > mMax) angle = mMax;
		if(Math.abs(mLastAngle-angle) > 50)
			return;
		if(((int)angle) != mLastAngle)
		{
			
			handleMove(angle);
			log(x, y, angle);
			mLastAngle = (int)angle;
		}
	}
	
	private int getCenterX()
	{
		centerX = ((Window.getClientWidth() / 2) - (imgPointer.getWidth() / 2)) ;
		if(mIsPCInternetExplorer)
			centerX -= MSIE_CENTER_OFFSET[0];
		else
			centerX -= CENTER_OFFSET[0];
		return centerX;
	}	
	
	private void handleMove(double angle)
	{
		handleMove(angle, true);
	}
	private void handleMove(double angle, boolean fireEvent)
	{		
		int volume = getPercent(angle);
		if(fireEvent)
			ValueChangeEvent.fire(this, volume);
		lblVolume.setText(String.valueOf(volume));
		angle = angle + 90;//offset		
		angle = angle * Math.PI/180;
		double posX = (Math.cos(angle) * lenFromCenter) + getCenterX() - leftOffset - imgPointerHalfSize;
		double posY = (Math.sin(angle) * lenFromCenter) + centerY - topOffset - imgPointerHalfSize;
		imgPointer.getElement().getStyle().setLeft(posX, Unit.PX);
		imgPointer.getElement().getStyle().setTop(posY, Unit.PX);
	}
	
	private void log(int x, int y, double angle)
	{
		//lblInfo.setText("X:" + x + " Y:" + y + "A:" + Math.round(angle) +" CX:" + centerX + " CY:" + centerY);
	}
	
	@UiHandler("imgBackground")
	void onImgBackgroundMouseMove(MouseMoveEvent event) 
	{		
		handleMove(event.getClientX(),event.getClientY());
	}
	
	/**
	 * 
	 * @param angle in degrees
	 * @return
	 */
	private int getPercent(double angle)
	{
		return (int)((angle - mMin) / 3); //(330 - 30)/100 => (max - min) / 100
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler)
	{
		return mHandler.addHandler(ValueChangeEvent.getType(), handler);
	}

	@Override
	public Integer getValue()
	{
		return mLastAngle;
	}

	@Override
	public void setValue(Integer value)
	{
		setValue(value, false);
	}

	@Override
	public void setValue(Integer value, boolean fireEvents)
	{
		mLastAngle = value;
		handleMove((value * 300 + 30));
		if(fireEvents)
		{
			ValueChangeEvent.fire(this, value);
		}
	}
	
	@Override
	public void fireEvent(com.google.gwt.event.shared.GwtEvent<?> event) 
	{
		mHandler.fireEvent(event);
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return null;
	};
}

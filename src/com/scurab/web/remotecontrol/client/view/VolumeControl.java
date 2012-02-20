package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.scurab.web.remotecontrol.client.tools.RCMath;

public class VolumeControl extends Composite implements HasValue<Integer>
{

	private static VolumeControlUiBinder uiBinder = GWT.create(VolumeControlUiBinder.class);
	@UiField Label lblInfo;
	@UiField Image imgPointer;
	@UiField Image imgBackground;
	@UiField Label lblVolume;
	
	private int centerX;
	private int centerY;
	private int lenFromCenter = centerX - 85;
	private int imgPointerHalfSize = 0;
	private int leftOffset = 0;
	private int topOffset = 0;
	private int mMin = 30;
	private int mMax = 330;
	private int mLastAngle = 0;

	interface VolumeControlUiBinder extends UiBinder<Widget, VolumeControl>
	{
		
	}

	public VolumeControl()
	{
		initWidget(uiBinder.createAndBindUi(this));
		imgPointer.setVisible(false);
	}
	
	@Override
	protected void onLoad()
	{
		super.onLoad();
		Timer t = new Timer(){@Override public void run(){init();}};
		t.schedule(100);
		
	}
	
	private void init()
	{
		int width = imgBackground.getWidth();
		int height = imgBackground.getHeight();
		leftOffset = imgBackground.getAbsoluteLeft();
		topOffset = imgBackground.getAbsoluteTop(); 
		centerX = leftOffset + (width / 2);
		centerY = topOffset + (height / 2);
		imgPointerHalfSize = imgPointer.getWidth() / 2;
		Timer t = new Timer(){@Override public void run(){imgPointer.setVisible(true);}};
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
		double angle = RCMath.getAngle(x, y, centerX, centerY);
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
	
	private void handleMove(double angle)
	{		
		lblVolume.setText(String.valueOf(getPercent(angle)));
		angle = angle + 90;//offset		
		angle = angle * Math.PI/180;
		double posX = (Math.cos(angle) * lenFromCenter) + centerX - leftOffset - imgPointerHalfSize;
		double posY = (Math.sin(angle) * lenFromCenter) + centerY - topOffset - imgPointerHalfSize;
		imgPointer.getElement().getStyle().setLeft(posX, Unit.PX);
		imgPointer.getElement().getStyle().setTop(posY, Unit.PX);
	}
	
	private void log(int x, int y, double angle)
	{
		lblInfo.setText("X:" + x + " Y:" + y + "A:" + Math.round(angle) +" CX:" + centerX + " CY:" + centerY);
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
		return addValueChangeHandler(handler);
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
			ValueChangeEvent.fire(this, value);
	}
}

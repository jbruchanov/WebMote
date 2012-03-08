package com.scurab.web.remotecontrol.client.view;

import java.util.HashMap;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.scurab.web.remotecontrol.client.event.ChangePresenterEvent;
import com.scurab.web.remotecontrol.client.event.ChangePresenterEventHandler;
import com.scurab.web.remotecontrol.client.presenter.BasePresenter;
import com.scurab.web.remotecontrol.client.presenter.MainViewPresenter;
import com.scurab.web.remotecontrol.client.server.DataService;

public class RootView extends Composite
{
	private final static DataService sDataService = new DataService();
	private final static HandlerManager sEventBus = new HandlerManager(null);
	private HashMap<String, BasePresenter> mWorkingComposites = null;
	private MainViewPresenter mMainViewPresenter = null;
	
	public RootView()
	{
		mWorkingComposites = new HashMap<String, BasePresenter>();
		bind();
		init();
	}
	
	@Override
	protected void onLoad()
	{
		super.onLoad();
		Window.scrollTo(0, 1);		
	}
	
	private void bind()
	{
		sEventBus.addHandler(ChangePresenterEvent.TYPE, new ChangePresenterEventHandler()
		{
			@Override
			public void onChangePresenter(ChangePresenterEvent event)
			{
				onChangePresenterImpl(event.getPresenter());
			}
		});
		History.addValueChangeHandler(new ValueChangeHandler<String>()
		{
			@Override
			public void onValueChange(ValueChangeEvent<String> event)
			{
				onHistoryChange(event.getValue());
			}
		});
		
		Window.addWindowClosingHandler(new Window.ClosingHandler()
		{
			public void onWindowClosing(Window.ClosingEvent closingEvent)
			{
				closingEvent.setMessage("Do you really want to leave the page?");
			}
		});
	}
	
	protected void onHistoryChange(String token)
	{
		if(token != null && token.length() > 0)
		{
			BasePresenter bp = mWorkingComposites.get(token);
			if(bp != null)
				onChangePresenterImpl(bp);
		}
		else
			onChangePresenterImpl(mMainViewPresenter);
			
	}
	
	private void init()
	{
		RootPanel.get("content").clear();
		mMainViewPresenter = new MainViewPresenter(sDataService,sEventBus,new MainView());
		HTMLPanel p = new HTMLPanel("A");
		initWidget(p.asWidget());
		onChangePresenterImpl(mMainViewPresenter);
	}
		
	protected void onChangePresenterImpl(BasePresenter bp)
	{	
		History.newItem(bp.getName(), false);
		mWorkingComposites.put(bp.getName(), bp);
		RootPanel.get("content").clear();
		RootPanel.get("content").add(bp.asWidget());
	}
}

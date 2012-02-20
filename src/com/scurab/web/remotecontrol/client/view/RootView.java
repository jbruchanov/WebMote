package com.scurab.web.remotecontrol.client.view;

import java.util.HashMap;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
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
	}
	
	private void init()
	{
		RootPanel.get("content").clear();
		initWidget(new MainViewPresenter(sDataService,sEventBus,new MainView()).asWidget());
	}
		
	protected void onChangePresenterImpl(BasePresenter bp)
	{
		RootPanel.get("content").clear();
		RootPanel.get("content").add(bp.asWidget());
	}
	
	
}

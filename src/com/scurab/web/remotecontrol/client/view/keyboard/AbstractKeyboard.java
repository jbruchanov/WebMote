package com.scurab.web.remotecontrol.client.view.keyboard;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractKeyboard extends Composite implements HasWidgets
{
	public abstract HasWidgets getContainer();
	
	@Override
	public void add(Widget w)
	{
		getContainer().add(w);
	}

	@Override
	public void clear()
	{
		getContainer().clear();
	}

	@Override
	public Iterator<Widget> iterator()
	{
		return getContainer().iterator();
	}

	@Override
	public boolean remove(Widget w)
	{
		return getContainer().remove(w);
	}
}

package com.scurab.web.remotecontrol.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public abstract class AbstractView extends Composite {
    public abstract List<IsCommandableClickHandler> getClickElements();
}

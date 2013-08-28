package com.scurab.web.remotecontrol.client.presenter;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.datamodel.KeyValueItem;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.AbstractView;

public abstract class BaseControlPresenter extends BasePresenter {
    private AbstractView mDisplay = null;

    public BaseControlPresenter(DataService dataService,
            HandlerManager eventBus, AbstractView display) {
        super(dataService, eventBus, display);
        mDisplay = display;
        onBindCommandButtons();
    }

    /**
     * Don't use it by default, it's only for calling from MediaCenter, to
     * override app to media center player
     * 
     * @param appName
     */
    public void setApplication(String appName) {

    }

    protected void onSendCommand(String command) {
        onSendCommand(getCommand(command));
    }

    protected void onSendCommand(Command command) {
        try {
            mDataService.sendCommand(command);
        } catch (Exception e) {
            Window.alert(e.getMessage());
        }
    }

    protected void onSendCommand(Command command, RequestCallback rc) {
        try {
            mDataService.sendCommand(command, rc);
        } catch (Exception e) {
            Window.alert(e.getMessage());
        }
    }

    protected void onBindCommandButtons() {
        if (mDisplay.getClickElements() == null) {
            return;
        }

        for (IsCommandableClickHandler ic : mDisplay.getClickElements()) {
            final IsCommandableClickHandler source = ic;
            ic.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    onSendCommand(source.getCommand());
                }
            });
        }
    }

    protected void initFavorities(ListBox lb, String key) {
        lb.clear();
        if (Storage.isLocalStorageSupported()) {
            List<KeyValueItem> items = RemoteControl.getFavorities(key);
            lb.addItem("", "");
            for (KeyValueItem kvi : items) {
                lb.addItem(kvi.Key, kvi.Value);
            }
        } else {
            lb.setEnabled(false);
        }
    }

    protected abstract Command getCommand(String command);
}

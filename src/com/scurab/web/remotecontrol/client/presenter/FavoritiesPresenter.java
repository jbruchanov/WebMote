package com.scurab.web.remotecontrol.client.presenter;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.R;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.controls.CommandButton;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.tools.StringUtils;
import com.scurab.web.remotecontrol.client.view.FavoritiesView;

public class FavoritiesPresenter extends BasePresenter {
    private FavoritiesView mDisplay = null;
    private Storage mStorage = Storage.getLocalStorageIfSupported();

    public FavoritiesPresenter(DataService dataService,
            HandlerManager eventBus, FavoritiesView display) {
        super(dataService, eventBus, display);
        mDisplay = display;

        bind();
    }

    private void bind() {
        mDisplay.getBtnAdd().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onAddItem(getCategory(), mDisplay.getTxtName().getValue(),
                        mDisplay.getTxtLink().getValue());
            }
        });

        mDisplay.getBtnDelete().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onDeleteItem(getCategory(), mDisplay.getTxtName().getValue(),
                        mDisplay.getTxtLink().getValue());
            }
        });

        ListBox cats = mDisplay.getCmbType();
        cats.addItem("", "");
        for (String s : new String[] { RemoteControl.PropertyKeys.AUDIOPLAYER,
                RemoteControl.PropertyKeys.PICTURESVIEWER,
                RemoteControl.PropertyKeys.VIDEOPLAYER }) {
            cats.addItem(s, s);
        }

        cats.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                onCategoryChange(getValue((ListBox) event.getSource()));
            }
        });
    }

    private void onCategoryChange(String category) {
        mDisplay.getItemContainer().clear();
        if (!isE(category)) {
            String data = mStorage.getItem(category);
            if (!isE(data)) {
                String[] rows = data.split(R.Constants.ITEM_SEPARATOR);
                for (String row : rows) {
                    String[] d = row.split(R.Constants.VALUE_SEPARATOR);
                    String n = d[0];
                    String v = d[1];
                    CommandButton b = getButton(n, v);
                    mDisplay.getItemContainer().add(b);
                }
            }
        }
    }

    private String getValue(ListBox lb) {
        return lb.getValue(lb.getSelectedIndex());
    }

    private String getCategory() {
        return mDisplay.getCmbType().getValue(
                mDisplay.getCmbType().getSelectedIndex());
    }

    @Override
    public String getName() {
        return "FavoritiesPresenter";
    }

    public void onAddItem(String category, String name, String value) {
        name = name.replace(R.Constants.VALUE_SEPARATOR, "");
        if (isE(category) || isE(name) || isE(value)) {
            Window.alert(RemoteControl.Words.MissingNameOrValue());
            return;
        }
        CommandButton b = getButton(name, value);
        mDisplay.getItemContainer().add(b);
        saveItems();
        clear();
    }

    private void clear() {
        mDisplay.getTxtName().setText("");
        mDisplay.getTxtLink().setText("");
    }

    private CommandButton getButton(final String n, final String v) {
        CommandButton b = new CommandButton();
        b.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onButtonClick(n, v);
            }
        });
        b.setText(n);
        b.setCommand(v);
        b.setHeight("40px");
        b.setWidth("100%");
        return b;
    }

    public void onButtonClick(String name, String value) {
        mDisplay.getTxtName().setText(name);
        mDisplay.getTxtLink().setText(value);
    }

    public void onDeleteItem(String category, String name, String value) {
        if (isE(category) || isE(name) || isE(value)) {
            Window.alert(RemoteControl.Words.MissingNameOrValue());
            return;
        }
        HasWidgets hw = mDisplay.getItemContainer();
        Iterator<Widget> iter = hw.iterator();
        while (iter.hasNext()) {
            CommandButton b = (CommandButton) iter.next();
            String n = b.getText();
            String v = b.getCommand();
            if (n.equals(name) && v.equals(value)) {
                mDisplay.getItemContainer().remove(b);
                saveItems();
                clear();
                return;
            }
        }
    }

    public boolean isE(String v) {
        return StringUtils.isEmpty(v);
    }

    protected void saveItems() {
        Storage s = Storage.getLocalStorageIfSupported();
        if (s == null) {
            Window.alert("Storage is not supported!");
            return;
        }
        String category = getCategory();
        s.removeItem(category);
        String items = getItems();
        s.setItem(category, items);
    }

    private String getItems() {
        StringBuilder sb = new StringBuilder();
        HasWidgets hw = mDisplay.getItemContainer();
        Iterator<Widget> iter = hw.iterator();
        while (iter.hasNext()) {
            CommandButton b = (CommandButton) iter.next();
            String name = b.getText();
            String value = b.getCommand();
            sb.append(name + R.Constants.VALUE_SEPARATOR + value
                    + R.Constants.ITEM_SEPARATOR);
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        } else {
            return "";
        }
    }

}

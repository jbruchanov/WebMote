package com.scurab.web.remotecontrol.client.view.keyboard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class DefaultKeyboard extends AbstractKeyboard {

    private static DefaultKeyboardUiBinder uiBinder = GWT
            .create(DefaultKeyboardUiBinder.class);
    @UiField
    HTMLPanel container;

    interface DefaultKeyboardUiBinder extends UiBinder<Widget, DefaultKeyboard> {
    }

    public DefaultKeyboard() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public HasWidgets getContainer() {
        return container;
    }
}

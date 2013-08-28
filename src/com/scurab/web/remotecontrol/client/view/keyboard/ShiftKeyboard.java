package com.scurab.web.remotecontrol.client.view.keyboard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ShiftKeyboard extends AbstractKeyboard {

    private static ShiftKeyboardUiBinder uiBinder = GWT
            .create(ShiftKeyboardUiBinder.class);

    interface ShiftKeyboardUiBinder extends UiBinder<Widget, ShiftKeyboard> {
    }

    @UiField
    HTMLPanel container;

    public ShiftKeyboard() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public HasWidgets getContainer() {
        return container;
    }

}

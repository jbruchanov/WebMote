package com.scurab.web.remotecontrol.client.view.keyboard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class SpecialKeyboard extends AbstractKeyboard {

    private static SpecialKeyboardUiBinder uiBinder = GWT
            .create(SpecialKeyboardUiBinder.class);
    @UiField
    HTMLPanel container;

    interface SpecialKeyboardUiBinder extends UiBinder<Widget, SpecialKeyboard> {
    }

    public SpecialKeyboard() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public HasWidgets getContainer() {
        return container;
    }

}

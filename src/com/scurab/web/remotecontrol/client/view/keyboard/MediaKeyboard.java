package com.scurab.web.remotecontrol.client.view.keyboard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MediaKeyboard extends AbstractKeyboard {

    private static MediaUiBinder uiBinder = GWT.create(MediaUiBinder.class);
    @UiField
    HTMLPanel container;

    interface MediaUiBinder extends UiBinder<Widget, MediaKeyboard> {
    }

    public MediaKeyboard() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public HasWidgets getContainer() {
        return container;
    }

}

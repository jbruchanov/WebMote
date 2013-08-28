package com.scurab.web.remotecontrol.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.controls.CommandButton;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;

public class JoyPadView extends AbstractView {

    private static JoyPadViewUiBinder uiBinder = GWT
            .create(JoyPadViewUiBinder.class);

    interface JoyPadViewUiBinder extends UiBinder<Widget, JoyPadView> {
    }

    public JoyPadView() {
        initWidget(uiBinder.createAndBindUi(this));

        mList = new ArrayList<IsCommandableClickHandler>();
        mList.addAll(joyPad.getClickElements());
        mList.addAll(Arrays.asList(new IsCommandableClickHandler[] { btnEscape,
                btnSpace }));
    }

    // @UiField ImageMobileButton btnDefault;
    @UiField
    CommandButton btnEscape;
    @UiField
    CommandButton btnSpace;
    // @UiField ImageMobileButton btnUser;
    @UiField
    JoyPad joyPad;

    private ArrayList<IsCommandableClickHandler> mList;

    // public ImageMobileButton getBtnDefault()
    // {
    // return btnDefault;
    // }
    //
    // public ImageMobileButton getBtnUser()
    // {
    // return btnUser;
    // }

    @Override
    public List<IsCommandableClickHandler> getClickElements() {
        return mList;
    }
}

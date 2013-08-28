package com.scurab.web.remotecontrol.client.controls;

import com.google.gwt.user.client.ui.ToggleButton;

public class KeyToggleButton extends ToggleButton {
    String mKeyCode = null;

    public String getKeyCode() {
        return mKeyCode;
    }

    public void setKeyCode(String keyCode) {
        mKeyCode = keyCode;
    }
}

package com.scurab.web.remotecontrol.client.controls;

import com.google.gwt.user.client.ui.Button;

public class KeyButton extends Button {
    private String mKeyCode = null;
    private boolean mNative = false;

    public String getKeyCode() {
        return mKeyCode;
    }

    public void setKeyCode(String keyCode) {
        mKeyCode = keyCode;
    }

    public boolean isNative() {
        return mNative;
    }

    public void setNative(boolean _native) {
        mNative = _native;
    }

}

package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class KeyboardCommand extends Command {
    private String mKeyCode = "";
    public final static String KEYCODE_KEY = "KeyCodes";

    private boolean mNative = false;
    public final static String NATIVE_KEY = "Native";

    @Override
    protected JSONObject getJsonObject() {
        JSONObject jso = super.getJsonObject();
        jso.put(KEYCODE_KEY, new JSONString(getKeyCode()));
        jso.put(NATIVE_KEY, new JSONString(String.valueOf(isNative())));
        return jso;
    }

    @Override
    protected String getCommand() {
        return "KeyboardCommand";
    }

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

package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class InfraRedCommand extends Command {
    private String mRemoteControl = null;
    public final static String RemoteControl_KEY = "RemoteControl";

    public InfraRedCommand(String rc) {
        mRemoteControl = rc;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject jso = super.getJsonObject();
        jso.put(RemoteControl_KEY, new JSONString(getRemoteControl()));
        return jso;
    }

    public void getAvailableCodes() {
        setMethod("__" + GET);
    }

    @Override
    public String getCommand() {
        return "InfraRedCommand";
    }

    public String getRemoteControl() {
        return mRemoteControl;
    }

    public void setRemoteControl(String remoteControl) {
        mRemoteControl = remoteControl;
    }

}

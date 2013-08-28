package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;

public class SystemCommand extends Command {
    public int Delay = 0;
    public final static String DELAY_KEY = "Delay";
    public boolean Force = true;
    public final static String FORCE_KEY = "Force";

    public void setDelay(int delay) {
        Delay = delay;
    }

    public void setForce(boolean force) {
        Force = force;
    }

    public void reboot() {
        setMethod("Reboot");
    }

    public void shutdown() {
        setMethod("Shutdown");
    }

    public void abort() {
        setMethod("Abort");
    }

    public void hibernate() {
        setMethod("Hibernate");
    }

    public void turnMonitorOn() {
        setMethod("MonitorOn");
    }

    public void turnMonitorOff() {
        setMethod("MonitorOff");
    }

    @Override
    protected String getCommand() {
        return "SystemCommand";
    }

    @Override
    protected JSONObject getJsonObject() {
        JSONObject jso = super.getJsonObject();
        jso.put(DELAY_KEY, new JSONNumber(Delay));
        jso.put(FORCE_KEY, JSONBoolean.getInstance(Force));
        return jso;
    }

}

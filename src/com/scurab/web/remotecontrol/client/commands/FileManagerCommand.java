package com.scurab.web.remotecontrol.client.commands;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class FileManagerCommand extends Command {

    private final static String ROOT_KEY = "Root";
    private final static String FILTER_KEY = "Filter";

    private String mRoot = null;
    public String mFilter = null;

    public FileManagerCommand() {
        setMethod(GET);
    }

    @Override
    protected JSONObject getJsonObject() {
        JSONObject jso = super.getJsonObject();
        if (getRoot() != null) {
            jso.put(ROOT_KEY, new JSONString(getRoot()));
        }
        if (mFilter != null) {
            jso.put(FILTER_KEY, new JSONString(mFilter));
        }
        return jso;
    }

    @Override
    protected String getCommand() {
        return "FileManagerCommand";
    }

    public String getRoot() {
        return mRoot;
    }

    public void setRoot(String root) {
        mRoot = root;
    }
}

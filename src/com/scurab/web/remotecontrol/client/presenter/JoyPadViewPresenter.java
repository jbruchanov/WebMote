package com.scurab.web.remotecontrol.client.presenter;

import java.util.HashMap;

import com.google.gwt.event.shared.HandlerManager;
import com.scurab.web.remotecontrol.client.R;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.KeyboardCommand;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.JoyPad;
import com.scurab.web.remotecontrol.client.view.JoyPadView;

public class JoyPadViewPresenter extends BaseControlPresenter {
    private JoyPadView mDisplay = null;
    private HashMap<String, String> mTranslationMap = null;

    public JoyPadViewPresenter(DataService dataService,
            HandlerManager eventBus, JoyPadView display) {
        super(dataService, eventBus, display);
        mDisplay = display;
        mTranslationMap = new HashMap<String, String>();
        bind();
    }

    private void bind() {
        mTranslationMap = new HashMap<String, String>();
        mTranslationMap.put(JoyPad.COMMAND_LEFT, R.Constants.KEY_LEFT);
        mTranslationMap.put(JoyPad.COMMAND_RIGHT, R.Constants.KEY_RIGHT);
        mTranslationMap.put(JoyPad.COMMAND_UP, R.Constants.KEY_UP);
        mTranslationMap.put(JoyPad.COMMAND_DOWN, R.Constants.KEY_DOWN);
        mTranslationMap.put(JoyPad.COMMAND_CENTER, R.Constants.KEY_ENTER);
    }

    @Override
    protected Command getCommand(String command) {
        KeyboardCommand kbc = new KeyboardCommand();
        kbc.setKeyCode(translateCommand(command));
        return kbc;
    }

    private String translateCommand(String command) {
        if (mTranslationMap.containsKey(command)) {
            return mTranslationMap.get(command);
        } else {
            return command;
        }
    }

    @Override
    public String getName() {
        return "JoyPadViewPresenter";
    }

}

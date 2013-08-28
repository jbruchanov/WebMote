package com.scurab.web.remotecontrol.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.scurab.web.remotecontrol.client.R;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.KeyboardCommand;
import com.scurab.web.remotecontrol.client.controls.KeyButton;
import com.scurab.web.remotecontrol.client.controls.KeyToggleButton;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.KeyboardView;
import com.scurab.web.remotecontrol.client.view.keyboard.AbstractKeyboard;

public class KeyboardPresenter extends BaseControlPresenter {
    private KeyboardView mDisplay = null;
    private AbstractKeyboard mCurrentKeyboard = null;
    private boolean mShifted = false;

    public KeyboardPresenter(DataService dataService, HandlerManager eventBus,
            KeyboardView display) {
        super(dataService, eventBus, display);
        mDisplay = display;
        bind();
    }

    private enum ShowPanel {
        Default, Shift, Special, Arrows, Numeric, Functional, Media
    }

    private void bind() {
        mDisplay.getShiftKeyboard().setVisible(false);
        mDisplay.getSpecialKeyboard().setVisible(false);
        mDisplay.getArrowsKeyboard().setVisible(false);
        mDisplay.getFuncKeyboard().setVisible(false);
        mDisplay.getMediaKeyboard().setVisible(false);
        mDisplay.getNumericKeyboard().setVisible(false);

        mDisplay.getBtnArrows().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onClickButton(ShowPanel.Arrows);
            }
        });
        mDisplay.getBtnDefault().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onClickButton(ShowPanel.Default);
            }
        });
        mDisplay.getBtnFunctional().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onClickButton(ShowPanel.Functional);
            }
        });
        mDisplay.getBtnMedia().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onClickButton(ShowPanel.Media);
            }
        });
        mDisplay.getBtnNumeric().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onClickButton(ShowPanel.Numeric);
            }
        });
        mDisplay.getBtnSpecial().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onClickButton(ShowPanel.Special);
            }
        });

        onClickButton(ShowPanel.Default);

        for (final KeyButton kb : mDisplay.getAllButtons()) {
            kb.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    onKeyClick(kb);
                }
            });
        }

        for (final KeyToggleButton kb : mDisplay.getAllToggleButtons()) {
            kb.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    onKeyClick(kb);
                }
            });
        }
    }

    protected void onKeyClick(KeyButton kb) {
        if ("SHIFT".equals(kb.getText()) || "SHFT".equals(kb.getText())) {
            if (mShifted) {
                onClickButton(ShowPanel.Default);
                mShifted = false;
            } else {
                onClickButton(ShowPanel.Shift);
                mShifted = true;
            }

        } else {
            String cmd = kb.getKeyCode() == null ? kb.getText() : kb
                    .getKeyCode();
            KeyboardCommand kc = new KeyboardCommand();
            kc.setNative(kb.isNative() || cmd.startsWith("{"));
            if (mShifted) {
                kc.setKeyCode(R.Constants.KEY_SHIFT);
            }
            kc.setKeyCode(kc.getKeyCode() + (getSpecialKeys() + cmd));
            onSendCommand(kc);
        }
    }

    private String getSpecialKeys() {
        StringBuilder sb = new StringBuilder();
        for (KeyToggleButton ktb : mDisplay.getAllToggleButtons()) {
            if (ktb.getValue() != null && ktb.getValue()) {
                sb.append(ktb.getKeyCode());
            }
        }
        return sb.toString();
    }

    protected void onKeyClick(KeyToggleButton kb) {
        String cmd = kb.getKeyCode() == null ? kb.getText() : kb.getKeyCode();
        KeyboardCommand kc = new KeyboardCommand();
        if (mShifted) {
            kc.setKeyCode(R.Constants.KEY_SHIFT);
        }
        kc.setKeyCode(kc.getKeyCode() + cmd);

        onSendCommand(kc);
    }

    private void resetAllToggleButtons() {
        for (final KeyToggleButton kb : mDisplay.getAllToggleButtons()) {
            kb.setValue(false);
        }
    }

    protected void onClickButton(ShowPanel what) {
        if (mCurrentKeyboard != null) {
            mCurrentKeyboard.setVisible(false);
        }

        resetAllToggleButtons();
        mCurrentKeyboard = null;
        switch (what) {
            case Default:
                mCurrentKeyboard = mDisplay.getDefaultKeyboard();
                break;
            case Shift:
                mCurrentKeyboard = mDisplay.getShiftKeyboard();
                break;
            case Special:
                mCurrentKeyboard = mDisplay.getSpecialKeyboard();
                break;
            case Arrows:
                mCurrentKeyboard = mDisplay.getArrowsKeyboard();
                break;
            case Functional:
                mCurrentKeyboard = mDisplay.getFuncKeyboard();
                break;
            case Media:
                mCurrentKeyboard = mDisplay.getMediaKeyboard();
                break;
            case Numeric:
                mCurrentKeyboard = mDisplay.getNumericKeyboard();
                break;
        }
        mShifted = false;
        if (mCurrentKeyboard != null) {
            mCurrentKeyboard.setVisible(true);
        }
    }

    @Override
    protected Command getCommand(String command) {
        return null;
    }

    @Override
    public String getName() {
        return "KeyboardPresenter";
    }

}

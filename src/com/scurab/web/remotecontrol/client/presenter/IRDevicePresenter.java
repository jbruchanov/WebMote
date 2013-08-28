package com.scurab.web.remotecontrol.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Header;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.scurab.web.remotecontrol.client.RemoteControl;
import com.scurab.web.remotecontrol.client.commands.Command;
import com.scurab.web.remotecontrol.client.commands.InfraRedCommand;
import com.scurab.web.remotecontrol.client.controls.CommandButton;
import com.scurab.web.remotecontrol.client.server.DataService;
import com.scurab.web.remotecontrol.client.view.IRDeviceView;

public class IRDevicePresenter extends BaseControlPresenter {
    IRDeviceView mDisplay = null;

    public IRDevicePresenter(DataService dataService, HandlerManager eventBus,
            IRDeviceView display) {
        super(dataService, eventBus, display);
        mDisplay = display;
        // mDataService = new MockDataService();
        bind();
        loadDefinitions();
    }

    private void bind() {
        mDisplay.getLblName().setText(RemoteControl.IRDevice);
    }

    private void loadDefinitions() {
        InfraRedCommand wlc = new InfraRedCommand(RemoteControl.IRDevice);
        wlc.getAvailableCodes();
        onSendCommand(wlc, new RequestCallback() {
            @Override
            public void onResponseReceived(Request request, Response response) {
                try {
                    String json = response.getText();
                    JSONValue v = JSONParser.parseStrict(json);
                    JSONArray array = v.isArray();
                    List<String> commands = new ArrayList<String>();
                    for (int i = 0, n = array.size(); i < n; i++) {
                        commands.add(array.get(i).isString().stringValue());
                    }
                    onLoadedDefinitions(commands);
                } catch (Exception e) {
                    Window.alert(e.getMessage());
                }
            }

            @Override
            public void onError(Request request, Throwable exception) {
                Window.alert(exception.getMessage());
            }
        });
    }

    protected void onLoadedDefinitions(List<String> data) {
        build(data);
    }

    protected void build(List<String> data) {
        for (String key : data) {
            final CommandButton cb = new CommandButton();
            cb.setStyleName("rc-IRButton gwt-Button");
            cb.setText(key);
            cb.setCommand(key);
            mDisplay.getContainer().add(cb);
            cb.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    onCommandButtonClick(cb.getCommand());
                }
            });
        }
    }

    public void onCommandButtonClick(String command) {
        onSendCommand(getCommand(command));
    }

    @Override
    protected Command getCommand(String command) {
        InfraRedCommand wlc = new InfraRedCommand(RemoteControl.IRDevice);
        wlc.setMethod(command);
        return wlc;
    }

    @Override
    public String getName() {
        return "IRDevicePresenter";
    }

    private class MockDataService extends DataService {
        public MockDataService() {
        }

        @Override
        public void sendCommand(Command c, RequestCallback rc)
                throws RequestException {
            Response r = new Response() {
                @Override
                public String getText() {
                    return "[\"power\",\"test\",\"input\"]";
                }

                @Override
                public String getStatusText() {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public int getStatusCode() {
                    // TODO Auto-generated method stub
                    return 0;
                }

                @Override
                public String getHeadersAsString() {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public Header[] getHeaders() {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public String getHeader(String header) {
                    // TODO Auto-generated method stub
                    return null;
                }
            };
            rc.onResponseReceived(null, r);
        }
    }
}

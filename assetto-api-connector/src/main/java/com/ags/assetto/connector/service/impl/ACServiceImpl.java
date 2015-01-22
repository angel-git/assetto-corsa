package com.ags.assetto.connector.service.impl;

import com.ags.assetto.connector.ACConnector;
import com.ags.assetto.connector.service.ACService;

import java.io.IOException;

/**
 * Created by Gavalda on 1/22/2015.
 */
@org.springframework.stereotype.Service
public class ACServiceImpl implements ACService {


    private Thread thread;
    private ACConnector connector;

    @Override
    public void prepareConnection(final String ipAddress) {
            try {
                if (thread == null) {
                    connector = new ACConnector(ipAddress);
                    thread = new Thread(connector);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void connect() {
        if (thread.getState().equals(Thread.State.NEW)) {
            thread.start();
        }
    }

    @Override
    public boolean isConnected() {
        if (connector != null) {
            return connector.isConnected();
        } else {
            return false;
        }
    }
}

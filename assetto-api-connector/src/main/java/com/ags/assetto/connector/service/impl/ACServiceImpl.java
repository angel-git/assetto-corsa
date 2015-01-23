package com.ags.assetto.connector.service.impl;

import com.ags.assetto.connector.ACConnector;
import com.ags.assetto.connector.service.ACService;
import com.ags.assetto.connector.vo.AssettoCorsaSocketIntoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * Created by Gavalda on 1/22/2015.
 */
@org.springframework.stereotype.Service
public class ACServiceImpl implements ACService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ACServiceImpl.class);

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
        LOGGER.debug("trying to connect...");
        if (thread.getState().equals(Thread.State.NEW)) {
            thread.start();
            LOGGER.debug("connection thread started");
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

    @Override
    public AssettoCorsaSocketIntoVo getData() {
        return connector.getData();
    }

    @Override
    @PreDestroy
    public void disconnect() {
        LOGGER.info("disconnecting....");
        connector.disconnect();
    }
}

package com.ags.assetto.connector.service;

/**
 * Created by Gavalda on 1/22/2015.
 */
public interface ACService {

    /**
     * opens the connection to AC game
     * @param ipAddress
     */
    public void connect(String ipAddress);

    /**
     * Checks if the race has started.
     * TODO: change this for websocket connection?
     * @return
     */
    public boolean isConnected();
}

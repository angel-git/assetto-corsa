package com.ags.assetto.connector.service;

/**
 * Created by Gavalda on 1/22/2015.
 */
public interface ACService {


    public void prepareConnection(String ipAddress);

    /**
     * opens the connection to AC game
     */
    public void connect();

    /**
     * Checks if the race has started.
     * TODO: change this for websocket connection?
     * @return
     */
    public boolean isConnected();
}

package com.ags.assetto.connector.service;

import com.ags.assetto.connector.vo.AssettoCorsaSocketIntoVo;

/**
 * Created by Gavalda on 1/22/2015.
 */
public interface ACService {


    /**
     * prepares the objects
     * @param ipAddress
     */
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


    /**
     * gets the assetto information from the socket
     * @return
     */
    public AssettoCorsaSocketIntoVo getData();


}

package com.ags.assetto.connector.utils;

/**
 * Operations to send to the socket.
 * Created by Gavalda on 1/22/2015.
 */
public enum OperationEnum {

    HANDSHAKE(0),
    SUBSCRIBE_UPDATE(1),
    SUBSCRIBE_SPOT(2),
    DISMISS(3);

    private int value;

    private OperationEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

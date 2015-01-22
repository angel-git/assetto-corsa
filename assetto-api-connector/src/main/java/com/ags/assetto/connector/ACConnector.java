package com.ags.assetto.connector;

import com.ags.assetto.connector.utils.OperationEnum;
import com.ags.assetto.connector.utils.ReaderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Gavalda on 1/22/2015.
 */
public class ACConnector implements Runnable {

    private String gameHost = "127.0.0.1";

    private static final Logger LOGGER = LoggerFactory.getLogger(ACConnector.class);

    private DatagramSocket socket;
    private DatagramPacket packet;
    private byte[] buffer;
    private String carName;
    private String driverName;
    private String trackName;
    private String trackConfig;
    private boolean updating;
    private boolean connected;


    public ACConnector(String host) throws IOException {
        this.gameHost = host;
        this.init();
    }


    @Override
    public void run() {
        try {
            initHandshake();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        this.buffer = new byte[1024];
        this.packet = new DatagramPacket(this.buffer, this.buffer.length);
        this.socket = new DatagramSocket();
        this.socket.setSoTimeout(1000);
        this.updating = true;
    }


    private void initHandshake() throws InterruptedException {

        try {
            ACConnector.HandshakeHandler var3 = new ACConnector.HandshakeHandler();
            var3.start();
            LOGGER.debug("Handshake start preparation ...");
            sendHandshake(OperationEnum.HANDSHAKE);
            LOGGER.debug("Handshake start sent");

            while(var3.isAlive()) {
                Thread.sleep(100L);
            }

            if(this.carName == null && this.driverName == null && this.trackName == null && this.trackConfig == null) {
                LOGGER.warn("Connection lost... retrying...");
                initHandshake();
            } else {
                Updater var4 = new Updater();
                var4.start();
                LOGGER.debug("Handshake connection confirmation preparation ...");
                this.sendHandshake(OperationEnum.SUBSCRIBE_UPDATE);
                LOGGER.debug("Handshake connection confirmation sent");

                while(var4.isAlive()) {
                    Thread.sleep(1000L);
                }

                LOGGER.debug("Handshake connection dismiss preparation ...");
                this.sendHandshake(OperationEnum.DISMISS);
                LOGGER.debug("Handshake connection dismiss sent");
                this.socket.close();
                LOGGER.warn("Socket closed");
            }


        } catch (IOException e) {
            try {
                LOGGER.info("no connection... retrying...");
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            initHandshake();
        }
    }


    private void sendHandshake(OperationEnum operationEnum) throws IOException {
        byte[] allocation = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN).putInt(0).putInt(0).putInt(operationEnum.getValue()).array();
        DatagramPacket datagramPacket = new DatagramPacket(allocation, allocation.length, InetAddress.getByName(gameHost), 9996);
        this.socket.send(datagramPacket);
    }

    private void readHandshakeResponse(byte[] var1) {
        this.carName = ReaderUtils.readStringUTF16LE(var1, 0, 100);
        this.driverName = ReaderUtils.readStringUTF16LE(var1, 100, 100);
        this.trackName = ReaderUtils.readStringUTF16LE(var1, 208, 100);
        this.trackConfig = ReaderUtils.readStringUTF16LE(var1, 308, 100);
    }

    private void readDataUpdate(byte[] var1) {
        char var2 = ReaderUtils.readChar(var1[0]);
        int var3 = ReaderUtils.readUInt32LE(var1, 4);
        if(var2 != 97) {
            throw new RuntimeException("Bad \'identifier\' in data update: " + var2);
        } else if(var3 != 328) {
            throw new RuntimeException("Bad \'size\' in data update: " + var3);
        } else {
            float speed_Kmh = ReaderUtils.readFloat32(var1, 8);
            float speed_Mph = ReaderUtils.readFloat32(var1, 12);
            float speed_Ms = ReaderUtils.readFloat32(var1, 16);
            boolean isAbsEnabled = ReaderUtils.readBoolean(var1[20]);
            boolean isAbsInAction = ReaderUtils.readBoolean(var1[21]);
            boolean isTcInAction = ReaderUtils.readBoolean(var1[22]);
            boolean isTcEnabled = ReaderUtils.readBoolean(var1[23]);
            boolean isInPit = ReaderUtils.readBoolean(var1[26]);
            boolean isEngineLimiterOn = ReaderUtils.readBoolean(var1[27]);
            float accG_vertical = ReaderUtils.readFloat32(var1, 28);
            float accG_horizontal = ReaderUtils.readFloat32(var1, 32);
            float accG_frontal = ReaderUtils.readFloat32(var1, 36);
            int lapTime = ReaderUtils.readUInt32LE(var1, 40);
            int lastLap = ReaderUtils.readUInt32LE(var1, 44);
            int bestLap = ReaderUtils.readUInt32LE(var1, 48);
            int lapCount = ReaderUtils.readUInt32LE(var1, 52);
            float gas = ReaderUtils.readFloat32(var1, 56);
            float brake = ReaderUtils.readFloat32(var1, 60);
            float clutch = ReaderUtils.readFloat32(var1, 64);
            float engineRPM = ReaderUtils.readFloat32(var1, 68);
            float steer = ReaderUtils.readFloat32(var1, 72);
            int gear = ReaderUtils.readUInt32LE(var1, 76);
            float cgHeight = ReaderUtils.readFloat32(var1, 80);
            float[] wheelAngularSpeed = ReaderUtils.read4Float32(var1, 84);
            float[] slipAngle = ReaderUtils.read4Float32(var1, 100);
            float[] slipAngle_ContactPatch = ReaderUtils.read4Float32(var1, 116);
            float[] slipRatio = ReaderUtils.read4Float32(var1, 132);
            float[] tyreSlip = ReaderUtils.read4Float32(var1, 148);
            float[] ndSlip = ReaderUtils.read4Float32(var1, 164);
            float[] load = ReaderUtils.read4Float32(var1, 180);
            float[] Dy = ReaderUtils.read4Float32(var1, 196);
            float[] Mz = ReaderUtils.read4Float32(var1, 212);
            float[] tyreDirtyLevel = ReaderUtils.read4Float32(var1, 228);
            float[] camberRAD = ReaderUtils.read4Float32(var1, 244);
            float[] tyreRadius = ReaderUtils.read4Float32(var1, 260);
            float[] tyreLoadedRadius = ReaderUtils.read4Float32(var1, 276);
            float[] suspensionHeight = ReaderUtils.read4Float32(var1, 292);
            float carPositionNormalized = ReaderUtils.readFloat32(var1, 308);
            float carSlope = ReaderUtils.readFloat32(var1, 312);
            float[] carCoordinates = ReaderUtils.read3Float32(var1, 316);
        }
    }

    public boolean isConnected() {
        return connected;
    }

    private class HandshakeHandler extends Thread {
        private HandshakeHandler() {
        }

        public void run() {
            try {
                //assetto corsa closes the connection if nothing happened!

                LOGGER.debug("Waiting for handshake response ...");
                ACConnector.this.socket.receive(ACConnector.this.packet);
                LOGGER.debug("Handshake response received");
                ACConnector.this.readHandshakeResponse(ACConnector.this.buffer);
                connected = true;
            } catch (SocketTimeoutException timeout) {
                LOGGER.error("Handshake response timeout");
            } catch (IOException ioExp) {
                LOGGER.error("error on handshake", ioExp);
            }

        }
    }


    private class Updater extends Thread {
        private Updater() {
        }

        public void run() {
            while(true) {
                try {
                    if(ACConnector.this.updating) {
                        LOGGER.debug("Waiting for data update ...");
                        ACConnector.this.socket.receive(ACConnector.this.packet);
                        LOGGER.debug("Data update received");
                        ACConnector.this.readDataUpdate(ACConnector.this.buffer);
                        continue;
                    }
                } catch (SocketTimeoutException sout) {
                    LOGGER.error("Data update timeout", sout);
                } catch (IOException io) {
                    LOGGER.error("Error receiving data", io);
                }
                return;
            }
        }
    }
}
